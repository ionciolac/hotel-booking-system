package com.hotel.booking.system.bookingservice.domain.service;

import com.hotel.booking.system.bookingservice.domain.client.HotelServiceClient;
import com.hotel.booking.system.bookingservice.domain.model.Booking;
import com.hotel.booking.system.bookingservice.ports.in.messaging.BookingRoomResponseListener;
import com.hotel.booking.system.bookingservice.ports.in.rest.BookingInPort;
import com.hotel.booking.system.bookingservice.ports.out.messaging.CreateBookingRoomPublisher;
import com.hotel.booking.system.bookingservice.ports.out.persistence.BookingOutPort;
import com.hotel.booking.system.common.common.BookingStatus;
import com.hotel.booking.system.common.domain.exception.BadRequestException;
import com.hotel.booking.system.common.domain.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.hotel.booking.system.common.common.BookingStatus.*;
import static com.hotel.booking.system.common.domain.utils.AppCommonMessages.*;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
@Service
public class BookingService implements BookingInPort, BookingRoomResponseListener {

    // out ports
    private final BookingOutPort bookingOutPort;
    private final CreateBookingRoomPublisher createBookingRoomPublisher;
    // external services
    private final HotelServiceClient hotelServiceClient;

    @Transactional
    @Override
    public Booking createBooking(Booking booking) {
        validateIfBookingExist(booking);
        validateIfRoomIsBookedThenThrowException(booking);
        booking.setStatus(RESERVED);
        booking.generateID();
        return bookingOutPort.upsertBooking(booking);
    }

    //TODO: add functionality to modify days of room bookings in hotel service in case when booking is in booked status
    @Transactional
    @Override
    public Booking updateBooking(Booking booking) {
        Booking dbBooking = getBookingFromDB(booking.getId());
        patch(dbBooking, booking);
        dbBooking = bookingOutPort.upsertBooking(dbBooking);
        return bookingOutPort.upsertBooking(dbBooking);
    }

    @Transactional
    @Override
    public void deleteBooking(UUID id) {
        Booking booking = getBookingFromDB(id);
        if (asList(RESERVED, ROOM_RESERVED, BOOKED_CANCELED).contains(booking.getStatus()))
            bookingOutPort.deleteBooking(id);
        else
            throw new BadRequestException(format(SERVICE_BOOKING_IS_BOOKED_AND_CANNOT_BE_REMOVED_MESSAGE));
    }

    @Override
    public Booking getBooking(UUID id) {
        return getBookingFromDB(id);
    }

    @Transactional
    @Override
    public Booking payBooking(UUID id) {
        Booking booking = getBookingFromDB(id);
        validateIfRoomIsNotInRESERVEDStatus(booking);
        validateIfRoomIsBookedThenThenStatusToROOM_RESERVED(booking);
        booking = bookingOutPort.upsertBooking(booking);
        createBookingRoomPublisher.publish(booking);
        return booking;
    }

    @Transactional
    @Override
    public void notifyRoomBookingStatus(UUID id, BookingStatus bookingStatus, UUID roomBookingId) {
        Booking booking = getBookingFromDB(id);
        booking.setStatus(bookingStatus);
        booking.setRoomBookingId(roomBookingId);
        bookingOutPort.upsertBooking(booking);
    }

    private void validateIfBookingExist(Booking booking) {
        var userId = booking.getUserId();
        var roomId = booking.getRoomId();
        var fromDate = booking.getFromDate();
        var toDate = booking.getToDate();
        if (bookingOutPort.getBooking(userId, roomId, fromDate, toDate).isPresent())
            throw new BadRequestException(format(SERVICE_BOOKING_IS_ALREADY_EXIST_MESSAGE, roomId, userId, fromDate, toDate));

    }

    private void validateIfRoomIsBookedThenThrowException(Booking booking) {
        var roomID = booking.getRoomId();
        var fromDate = booking.getFromDate();
        var toDate = booking.getToDate();
        if (isRoomBookedInHotelService(roomID, fromDate, toDate)) {
            throw new BadRequestException(format(SERVICE_ROOM_ALREADY_IS_BOOKED_FROM_TO_DATE_MESSAGE, roomID, fromDate, toDate));
        }
    }

    private void validateIfRoomIsBookedThenThenStatusToROOM_RESERVED(Booking booking) {
        var roomID = booking.getRoomId();
        var fromDate = booking.getFromDate();
        var toDate = booking.getToDate();
        if (isRoomBookedInHotelService(roomID, fromDate, toDate)) {
            booking.setStatus(ROOM_RESERVED);
        }
    }

    private boolean isRoomBookedInHotelService(UUID roomID, LocalDateTime fromDate, LocalDateTime toDate) {
        return requireNonNull(hotelServiceClient.checkIfRoomIsBooked(roomID, fromDate, toDate)
                .getBody()).isRoomIsBooked();
    }

    private Booking getBookingFromDB(UUID id) {
        Optional<Booking> booking = bookingOutPort.getBooking(id);
        if (booking.isPresent())
            return booking.get();
        else
            throw new NotFoundException(format(SERVICE_OBJECT_WAS_NOT_FOUND_IN_DB_MESSAGE, BOOKING, id));
    }

    private void patch(Booking target, Booking source) {
        if (source.getFromDate() != null) {
            target.setFromDate(source.getFromDate());
        }
        if (source.getToDate() != null) {
            target.setToDate(source.getToDate());
        }
    }

    private void validateIfRoomIsNotInRESERVEDStatus(Booking booking) {
        if (!RESERVED.equals(booking.getStatus())) {
            var msg = format(SERVICE_BOOKING_MUST_BE_IN_RESERVED_STATUS_MESSAGE, booking.getId(), RESERVED);
            throw new BadRequestException(msg);
        }
    }
}
