package com.hotel.booking.system.hotel.service.domain.service;

import com.hotel.booking.system.common.common.exception.BadRequestException;
import com.hotel.booking.system.common.common.exception.NotFoundException;
import com.hotel.booking.system.hotel.service.domain.config.HotelServiceConfigData;
import com.hotel.booking.system.hotel.service.domain.model.AvailableRoom;
import com.hotel.booking.system.hotel.service.domain.model.Room;
import com.hotel.booking.system.hotel.service.domain.model.RoomBooking;
import com.hotel.booking.system.hotel.service.domain.ports.in.messaging.BookingListener;
import com.hotel.booking.system.hotel.service.domain.ports.in.rest.BookingRoomInPort;
import com.hotel.booking.system.hotel.service.domain.ports.in.rest.RoomInPort;
import com.hotel.booking.system.hotel.service.domain.ports.out.messaging.BookingPublisher;
import com.hotel.booking.system.hotel.service.domain.ports.out.persistence.BookingRoomOutPort;
import com.hotel.booking.system.kafka.model.BookingMessage;
import com.hotel.booking.system.kafka.model.BookingMessageStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static com.hotel.booking.system.common.common.utils.AppCommonMessages.*;
import static com.hotel.booking.system.common.common.utils.AppConstants.SYSTEM_CHECKIN_HOUR;
import static com.hotel.booking.system.common.common.utils.AppConstants.SYSTEM_CHECKOUT_HOUR;
import static com.hotel.booking.system.common.common.utils.DateTimeUtils.addHourAndMinutesToYYYYmmDD;
import static com.hotel.booking.system.kafka.model.BookingMessageStatus.*;
import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class BookingRoomService implements BookingRoomInPort, BookingListener {

    // config
    private final HotelServiceConfigData hotelServiceConfigData;
    // out ports
    private final BookingRoomOutPort roomBookingRoomOutPort;
    private final BookingPublisher bookingPublisher;
    //services
    private final RoomInPort roomInPort;

    @Override
    public boolean checkIfRoomIsBooked(UUID roomId, UUID userId, LocalDateTime fromDate, LocalDateTime toDate) {
        validateReservationDates(fromDate, toDate);
        var searchFromDate = addHourAndMinutesToYYYYmmDD(fromDate, SYSTEM_CHECKIN_HOUR, 0);
        var searchToDate = addHourAndMinutesToYYYYmmDD(toDate, SYSTEM_CHECKOUT_HOUR, 0);
        return isRoomBooked(roomId, userId, searchFromDate, searchToDate);
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
    public void bookingConsumer(BookingMessage bookingMessage) {
        var bookingMessageStatus = bookingMessage.status();
        switch (bookingMessageStatus) {
            case CREATE_BOOKING -> {
                var topicName = hotelServiceConfigData.getCreatedBookingTopicName();
                var roomId = bookingMessage.roomId();
                var room = roomInPort.getRoom(roomId);
                var checkinHour = getCheckinHour(room);
                var checkoutHour = getCheckoutHour(room);
                var fromDate = bookingMessage.fromDate();
                var toDate = bookingMessage.toDate();
                validateReservationDates(fromDate, toDate);
                var searchFromDate = addHourAndMinutesToYYYYmmDD(fromDate, checkinHour, 0);
                var searchToDate = addHourAndMinutesToYYYYmmDD(toDate, checkoutHour, 0);
                var userId = bookingMessage.userId();
                if (isRoomBooked(roomId, userId, searchFromDate, searchToDate)) {
                    bookingMessage = toBookingMessage(bookingMessage, FAILED_CREATE_BOOKING);
                    bookingPublisher.publishBooking(topicName, bookingMessage.bookingId().toString(), bookingMessage);
                } else {
                    var nights = ChronoUnit.DAYS.between(fromDate, toDate);
                    var pricePerNight = room.getPricePerNight();
                    var roomBooking = RoomBooking.builder()
                            .room(room)
                            .userId(userId)
                            .bookingId(bookingMessage.bookingId())
                            .fromDate(addHourAndMinutesToYYYYmmDD(fromDate, checkinHour, 0))
                            .toDate(addHourAndMinutesToYYYYmmDD(toDate, checkoutHour, 0))
                            .nightsNumber(nights)
                            .pricePerNight(pricePerNight)
                            .totalPrice(nights * pricePerNight)
                            .currency(room.getCurrency())
                            .build();
                    roomBooking.generateID();
                    roomBooking = roomBookingRoomOutPort.upsertRoomBooking(roomBooking);
                    bookingMessage = toBookingMessage(roomBooking, BOOKING_CREATED);
                    bookingPublisher.publishBooking(topicName, bookingMessage.bookingId().toString(), bookingMessage);
                }
            }
            case UPDATE_BOOKING -> {
                var topicName = hotelServiceConfigData.getUpdatedBookingTopicName();
                var roomBooking = getDBRoomBooking(bookingMessage.roomBookingId());
                var roomId = bookingMessage.roomId();
                var room = roomInPort.getRoom(roomId);
                var fromDate = bookingMessage.fromDate();
                var toDate = bookingMessage.toDate();
                var checkinHour = getCheckinHour(room);
                var checkoutHour = getCheckoutHour(room);
                var nights = ChronoUnit.DAYS.between(fromDate, toDate);
                var pricePerNight = room.getPricePerNight();
                roomBooking.setFromDate(addHourAndMinutesToYYYYmmDD(fromDate, checkinHour, 0));
                roomBooking.setToDate(addHourAndMinutesToYYYYmmDD(toDate, checkoutHour, 0));
                roomBooking.setNightsNumber(nights);
                roomBooking.setPricePerNight(pricePerNight);
                roomBooking.setTotalPrice(nights * pricePerNight);
                roomBooking = roomBookingRoomOutPort.upsertRoomBooking(roomBooking);
                bookingMessage = toBookingMessage(roomBooking, BOOKING_UPDATED);
                bookingPublisher.publishBooking(topicName, bookingMessage.bookingId().toString(), bookingMessage);
            }
            case REMOVE_BOOKING -> {
                var id = bookingMessage.roomBookingId();
                var roomBooking = getDBRoomBooking(id);
                bookingMessage = toBookingMessage(roomBooking, BOOKING_REMOVED);
                roomBookingRoomOutPort.removeRoomBooking(id);
                var topicName = hotelServiceConfigData.getRemovedBookingTopicName();
                bookingPublisher.publishBooking(topicName, bookingMessage.bookingId().toString(), bookingMessage);
            }
        }
    }

    private RoomBooking getDBRoomBooking(UUID id) {
        var roomBooking = roomBookingRoomOutPort.getRoomBooking(id);
        if (roomBooking.isPresent())
            return roomBooking.get();
        else
            throw new NotFoundException(format(SERVICE_OBJECT_WAS_NOT_FOUND_IN_DB_MESSAGE, BOOKING, id));
    }

    private boolean isRoomBooked(UUID roomId, UUID userId, LocalDateTime fromDate, LocalDateTime toDate) {
        roomInPort.getRoom(roomId);
        return roomBookingRoomOutPort.checkIfRoomIsBooked(roomId, userId, fromDate, toDate);
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

    private BookingMessage toBookingMessage(RoomBooking roomBooking, BookingMessageStatus status) {
        return BookingMessage.builder()
                .bookingId(roomBooking.getBookingId())
                .roomBookingId(roomBooking.getId())
                .roomId(roomBooking.getRoom().getId())
                .userId(roomBooking.getUserId())
                .fromDate(roomBooking.getFromDate())
                .toDate(roomBooking.getToDate())
                .status(status)
                .build();
    }

    private BookingMessage toBookingMessage(BookingMessage bookingMessage, BookingMessageStatus status) {
        return BookingMessage.builder()
                .bookingId(bookingMessage.bookingId())
                .roomBookingId(bookingMessage.roomBookingId())
                .roomId(bookingMessage.roomId())
                .userId(bookingMessage.userId())
                .fromDate(bookingMessage.fromDate())
                .toDate(bookingMessage.toDate())
                .status(status)
                .build();
    }
}
