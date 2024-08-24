package com.hotel.booking.system.bookingservice.domain.service;

import com.hotel.booking.system.bookingservice.domain.client.HotelServiceClient;
import com.hotel.booking.system.bookingservice.domain.model.Booking;
import com.hotel.booking.system.bookingservice.ports.in.rest.BookingInPort;
import com.hotel.booking.system.bookingservice.ports.out.persistence.BookingOutPort;
import com.hotel.booking.system.common.domain.exception.BadRequestException;
import com.hotel.booking.system.common.domain.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.hotel.booking.system.common.common.BookingStatus.BOOKED_CANCELED;
import static com.hotel.booking.system.common.common.BookingStatus.RESERVED;
import static com.hotel.booking.system.common.domain.utils.AppCommonMessages.*;
import static java.lang.String.format;
import static java.util.Arrays.asList;

@RequiredArgsConstructor
@Service
public class BookingService implements BookingInPort {

    // repository
    private final BookingOutPort bookingOutPort;
    // service
    private final HotelServiceClient hotelServiceClient;

    @Override
    public Booking createBooking(Booking booking) {
        validateIfBookingExist(booking);
        checkIfRoomIsNotBooked(booking);
        booking.setStatus(RESERVED);
        booking.generateID();
        return bookingOutPort.upsertBooking(booking);
    }

    //TODO: add functionality to modify days of room bookings in hotel service in case when booking is in booked status
    @Override
    public Booking updateBooking(Booking booking) {
        Booking dbBooking = getBookingFromDB(booking.getId());
        patch(dbBooking, booking);
        dbBooking = bookingOutPort.upsertBooking(dbBooking);
        return bookingOutPort.upsertBooking(dbBooking);
    }

    @Override
    public void deleteBooking(UUID id) {
        Booking booking = getBookingFromDB(id);
        if (asList(RESERVED, BOOKED_CANCELED).contains(booking.getStatus()))
            bookingOutPort.deleteBooking(id);
        else
            throw new BadRequestException(format(SERVICE_BOOKING_IS_BOOKED_AND_CANNOT_BE_REMOVED_MESSAGE));
    }

    @Override
    public Booking getBooking(UUID id) {
        return getBookingFromDB(id);
    }

    private void validateIfBookingExist(Booking booking) {
        var userId = booking.getUserId();
        var roomId = booking.getRoomId();
        var fromDate = booking.getFromDate();
        var toDate = booking.getToDate();
        if (bookingOutPort.getBooking(userId, roomId, fromDate, toDate).isPresent())
            throw new BadRequestException(format(SERVICE_BOOKING_IS_ALREADY_EXIST_MESSAGE, roomId, userId, fromDate, toDate));

    }

    private void checkIfRoomIsNotBooked(Booking booking) {
        var roomID = booking.getRoomId();
        var fromDate = booking.getFromDate();
        var toDate = booking.getToDate();
        var response = hotelServiceClient.checkIfRoomIsBooked(roomID, fromDate, toDate);
        if (Objects.requireNonNull(response.getBody()).isRoomIsBooked()) {
            throw new BadRequestException(format(SERVICE_ROOM_ALREADY_IS_BOOKED_FROM_TO_DATE_MESSAGE, roomID, fromDate, toDate));
        }
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
}
