package com.hotel.booking.system.hotel.service.domain.service;

import com.hotel.booking.system.common.domain.exception.BadRequestException;
import com.hotel.booking.system.hotel.service.domain.model.RoomBooking;
import com.hotel.booking.system.hotel.service.ports.in.messaging.BookingListenerPort;
import com.hotel.booking.system.hotel.service.ports.in.rest.BookingInPort;
import com.hotel.booking.system.hotel.service.ports.in.rest.RoomInPort;
import com.hotel.booking.system.hotel.service.ports.out.BookingOutPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static com.hotel.booking.system.common.domain.utils.AppCommonMessages.SERVICE_RESERVATION_DATE_VALIDATION_MESSAGE;
import static com.hotel.booking.system.common.domain.utils.DateTimeUtils.addHourAndMinutesToYYYYmmDD;

@RequiredArgsConstructor
@Service
public class BookingService implements BookingInPort, BookingListenerPort {

    private final BookingOutPort roomBookingOutPort;
    //services
    private final RoomInPort roomInPort;

    @Override
    public boolean checkIfRoomIsBooked(UUID roomId, LocalDateTime fromDate, LocalDateTime toDate) {
        validateReservationDates(fromDate, toDate);
        return isRoomBooked(roomId, fromDate, toDate);
    }

    @Transactional
    @Override
    public void bookRoom(RoomBooking roomBooking) {
        var roomId = roomBooking.getRoom().getId();
        var dbRoom = roomInPort.getRoom(roomId);
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
}
