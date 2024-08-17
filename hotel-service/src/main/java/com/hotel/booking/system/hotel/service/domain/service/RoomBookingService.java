package com.hotel.booking.system.hotel.service.domain.service;

import com.hotel.booking.system.common.domain.exception.BadRequestException;
import com.hotel.booking.system.common.domain.exception.NotFoundException;
import com.hotel.booking.system.hotel.service.domain.model.Room;
import com.hotel.booking.system.hotel.service.domain.model.RoomBooking;
import com.hotel.booking.system.hotel.service.ports.in.messaging.BookRoomListener;
import com.hotel.booking.system.hotel.service.ports.in.rest.RoomBookingInPort;
import com.hotel.booking.system.hotel.service.ports.out.RoomBookingOutPort;
import com.hotel.booking.system.hotel.service.ports.out.RoomOutPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

import static com.hotel.booking.system.common.domain.utils.AppCommonMessages.*;
import static com.hotel.booking.system.common.domain.utils.DateTimeUtils.addHourAndMinutesToYYYYmmDD;
import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class RoomBookingService implements RoomBookingInPort, BookRoomListener {

    private final RoomBookingOutPort roomBookingOutPort;
    private final RoomOutPort roomOutPort;

    @Override
    public boolean checkIfRoomIsBooked(UUID roomId, LocalDateTime fromDate, LocalDateTime toDate) {
        validateReservationDates(fromDate, toDate);
        return isRoomBooked(roomId, fromDate, toDate);
    }

    @Transactional
    @Override
    public void bookRoom(RoomBooking roomBooking) {
        var roomId = roomBooking.getRoom().getId();
        var dbRoom = getDBRoom(roomId);
        var checkinHour = dbRoom.getHotel().getCheckinHour();
        var checkoutHour = dbRoom.getHotel().getCheckinHour();
        var fromDate = roomBooking.getFromDate();
        var toDate = roomBooking.getToDate();
        validateReservationDates(fromDate, toDate);
        var searchFromDate = addHourAndMinutesToYYYYmmDD(fromDate, checkinHour, 0);
        var searchToDate = addHourAndMinutesToYYYYmmDD(toDate, checkoutHour, 0);
        if (isRoomBooked(roomId, searchFromDate, searchToDate)) {
            System.out.println("room is already booked");
        } else {
            var nights = ChronoUnit.DAYS.between(fromDate, toDate);
            var pricePerNight = dbRoom.getPricePerNight();
            roomBooking.setRoom(dbRoom);
            roomBooking.setFromDate(addHourAndMinutesToYYYYmmDD(fromDate, checkinHour, 0));
            roomBooking.setToDate(addHourAndMinutesToYYYYmmDD(toDate, checkoutHour, 0));
            roomBooking.setPricePerNight(pricePerNight);
            roomBooking.setNightsNumber(nights);
            roomBooking.setTotalPrice(nights * pricePerNight);
            roomBooking.setCurrency(dbRoom.getCurrency());
            roomBooking.generateID();
            roomBookingOutPort.insertRoomBooking(roomBooking);
        }
    }

    private boolean isRoomBooked(UUID roomId, LocalDateTime fromDate, LocalDateTime toDate) {
        return roomBookingOutPort.checkIfRoomIsBooked(roomId, fromDate, toDate);
    }

    private void validateReservationDates(LocalDateTime fromDate, LocalDateTime toDate) {
        if (fromDate.isEqual(toDate) || fromDate.isAfter(toDate))
            throw new BadRequestException(SERVICE_RESERVATION_DATE_VALIDATION_MESSAGE);
    }

    private Room getDBRoom(UUID id) {
        Optional<Room> room = roomOutPort.getRoom(id);
        if (room.isPresent())
            return room.get();
        else
            throw new NotFoundException(format(SERVICE_OBJECT_WAS_NOT_FOUND_IN_DB_MESSAGE, ROOM, id));
    }
}
