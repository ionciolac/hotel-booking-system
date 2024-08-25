package com.hotel.booking.system.bookingservice.domain.service;

import com.hotel.booking.system.bookingservice.domain.client.HotelServiceClient;
import com.hotel.booking.system.bookingservice.domain.model.Booking;
import com.hotel.booking.system.bookingservice.ports.in.messaging.CreatedBookingRoomListener;
import com.hotel.booking.system.bookingservice.ports.in.messaging.RemovedBookingRoomListener;
import com.hotel.booking.system.bookingservice.ports.in.rest.BookingInPort;
import com.hotel.booking.system.bookingservice.ports.out.messaging.CreateBookingRoomPublisher;
import com.hotel.booking.system.bookingservice.ports.out.messaging.RemoveBookingRoomPublisher;
import com.hotel.booking.system.bookingservice.ports.out.persistence.BookingOutPort;
import com.hotel.booking.system.common.domain.exception.BadRequestException;
import com.hotel.booking.system.common.domain.exception.NotFoundException;
import com.hotel.booking.system.kafka.model.RemoveBookingMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.hotel.booking.system.common.common.BookingStatus.*;
import static com.hotel.booking.system.common.domain.utils.AppCommonMessages.*;
import static com.hotel.booking.system.kafka.model.BookingRemoveStatus.BOOKING_REMOVED;
import static com.hotel.booking.system.kafka.model.BookingRemoveStatus.REMOVE_BOOKING;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
@Service
public class BookingService implements BookingInPort, CreatedBookingRoomListener, RemovedBookingRoomListener {

    // out ports
    private final BookingOutPort bookingOutPort;
    private final CreateBookingRoomPublisher createBookingRoomPublisher;
    private final RemoveBookingRoomPublisher removeBookingRoomPublisher;
    // external services
    private final HotelServiceClient hotelServiceClient;

    @Transactional
    @Override
    public Booking createBooking(Booking booking) {
        validateIfBookingExist(booking);
        var result = isRoomBookedInHotelService(booking);
        booking.validateIfRoomIsBookedThenThrowException(result, booking);
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
        if (asList(RESERVED, ROOM_IS_ALREADY_BOOKED, BOOKED_CANCELED).contains(booking.getStatus()))
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
        booking.validateIfRoomIsNotInRESERVEDStatus(booking);
        var result = isRoomBookedInHotelService(booking);
        booking.validateIfRoomIsBookedThenThenStatusToROOM_RESERVED(result, booking);
        booking.setStatus(BOOKING_ROOM);
        booking = bookingOutPort.upsertBooking(booking);
        createBookingRoomPublisher.publish(booking);
        return booking;
    }

    @Override
    public Booking cancelBooking(UUID id) {
        Booking booking = getBookingFromDB(id);
        booking.validateIfCanCancelBooking(booking);
        booking.setStatus(INIT_CANCEL_BOOKING);
        booking = bookingOutPort.upsertBooking(booking);
        //TODO: make payment refund
        var removeBookingMessage = toRemoveBookingMessage(booking);
        removeBookingRoomPublisher.publish(removeBookingMessage);
        return booking;
    }

    // TODO if status is ROOM_BOOKED then change status to WAITING_PAYMENT and init payment
    @Transactional
    @Override
    public void bookingCreated(Booking booking) {
        Booking dbBooking = getBookingFromDB(booking.getId());
        dbBooking.setStatus(booking.getStatus());
        dbBooking.setRoomBookingId(booking.getRoomBookingId());
        dbBooking.setFromDate(booking.getFromDate());
        dbBooking.setToDate(booking.getToDate());
        bookingOutPort.upsertBooking(dbBooking);
    }

    @Override
    public void bookingRemoved(RemoveBookingMessage removeBookingMessage) {
        Booking booking = getBookingFromDB(removeBookingMessage.bookingId());
        if (removeBookingMessage.removeStatus().equals(BOOKING_REMOVED)) {
            booking.setStatus(BOOKED_CANCELED);
            bookingOutPort.upsertBooking(booking);
        }
    }

    private void validateIfBookingExist(Booking booking) {
        var userId = booking.getUserId();
        var roomId = booking.getRoomId();
        var fromDate = booking.getFromDate();
        var toDate = booking.getToDate();
        if (bookingOutPort.getBooking(userId, roomId, fromDate, toDate).isPresent())
            throw new BadRequestException(format(SERVICE_BOOKING_IS_ALREADY_EXIST_MESSAGE, roomId, userId, fromDate, toDate));

    }

    private boolean isRoomBookedInHotelService(Booking booking) {
        var roomID = booking.getRoomId();
        var fromDate = booking.getFromDate();
        var toDate = booking.getToDate();
        return isRoomBookedInHotelService(roomID, fromDate, toDate);
    }


    private boolean isRoomBookedInHotelService(UUID roomID, LocalDateTime fromDate, LocalDateTime toDate) {
        return requireNonNull(hotelServiceClient.checkIfRoomIsBooked(roomID, fromDate, toDate).getBody())
                .isRoomIsBooked();
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

    private RemoveBookingMessage toRemoveBookingMessage(Booking booking) {
        return RemoveBookingMessage.builder()
                .bookingId(booking.getId())
                .roomBookingId(booking.getRoomBookingId())
                .roomId(booking.getRoomId())
                .removeStatus(REMOVE_BOOKING)
                .build();
    }
}
