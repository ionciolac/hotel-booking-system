package com.hotel.booking.system.hotel.service.domain.service;

import com.hotel.booking.system.common.domain.exception.BadRequestException;
import com.hotel.booking.system.hotel.service.domain.model.AvailableRoom;
import com.hotel.booking.system.hotel.service.domain.model.Room;
import com.hotel.booking.system.hotel.service.domain.model.RoomBooking;
import com.hotel.booking.system.hotel.service.ports.in.messaging.BookingRoomListenerPort;
import com.hotel.booking.system.hotel.service.ports.in.rest.BookingRoomInPort;
import com.hotel.booking.system.hotel.service.ports.in.rest.RoomInPort;
import com.hotel.booking.system.hotel.service.ports.out.BookingRoomOutPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static com.hotel.booking.system.common.domain.utils.AppCommonMessages.SERVICE_RESERVATION_DATE_VALIDATION_MESSAGE;
import static com.hotel.booking.system.common.domain.utils.AppConstants.SYSTEM_CHECKIN_HOUR;
import static com.hotel.booking.system.common.domain.utils.AppConstants.SYSTEM_CHECKOUT_HOUR;
import static com.hotel.booking.system.common.domain.utils.DateTimeUtils.addHourAndMinutesToYYYYmmDD;

@RequiredArgsConstructor
@Service
public class BookingRoomService implements BookingRoomInPort, BookingRoomListenerPort {

    private final BookingRoomOutPort roomBookingRoomOutPort;
    //services
    private final RoomInPort roomInPort;

    @Override
    public boolean checkIfRoomIsBooked(UUID roomId, LocalDateTime fromDate, LocalDateTime toDate) {
        validateReservationDates(fromDate, toDate);
        var searchFromDate = addHourAndMinutesToYYYYmmDD(fromDate, SYSTEM_CHECKIN_HOUR, 0);
        var searchToDate = addHourAndMinutesToYYYYmmDD(toDate, SYSTEM_CHECKOUT_HOUR, 0);
        return isRoomBooked(roomId, searchFromDate, searchToDate);
    }

    @Override
    public Page<AvailableRoom> getAvailableRooms(String country, String city, LocalDateTime fromDate, LocalDateTime toDate,
                                                 Double minPricePerNight, Double maxPricePerNight, Pageable pageable) {
        return roomInPort.getRooms(country, city, fromDate, toDate, minPricePerNight, maxPricePerNight, pageable)
                .map(room -> AvailableRoom.builder()
                        .hotelId(room.getHotel().getId())
                        .hotelName(room.getHotel().getName())
                        .roomId(room.getId())
                        .pricePerNight(room.getPricePerNight())
                        .currency(room.getCurrency())
                        .country(room.getHotel().getAddress().getCountry())
                        .city(room.getHotel().getAddress().getCity())
                        .build());
    }

    @Transactional
    @Override
    public void bookRoom(RoomBooking roomBooking) {
        var roomId = roomBooking.getRoom().getId();
        var dbRoom = roomInPort.getRoom(roomId);
        var checkinHour = getCheckinHour(dbRoom);
        var checkoutHour = getCheckoutHour(dbRoom);
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
            roomBookingRoomOutPort.insertRoomBooking(roomBooking);
        }
    }

    private boolean isRoomBooked(UUID roomId, LocalDateTime fromDate, LocalDateTime toDate) {
        return roomBookingRoomOutPort.checkIfRoomIsBooked(roomId, fromDate, toDate);
    }

    private void validateReservationDates(LocalDateTime fromDate, LocalDateTime toDate) {
        if (fromDate.isEqual(toDate) || fromDate.isAfter(toDate))
            throw new BadRequestException(SERVICE_RESERVATION_DATE_VALIDATION_MESSAGE);
    }

    private int getCheckinHour(Room room) {
        var checkinHour = room.getHotel().getCheckinHour();
        return checkinHour == 0 ? SYSTEM_CHECKIN_HOUR : checkinHour;
    }

    private int getCheckoutHour(Room room) {
        var checkoutHour = room.getHotel().getCheckoutHour();
        return checkoutHour == 0 ? SYSTEM_CHECKOUT_HOUR : checkoutHour;
    }
}
