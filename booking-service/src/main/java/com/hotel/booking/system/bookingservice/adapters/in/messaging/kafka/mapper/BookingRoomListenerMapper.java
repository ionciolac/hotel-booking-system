package com.hotel.booking.system.bookingservice.adapters.in.messaging.kafka.mapper;

import com.hotel.booking.system.bookingservice.domain.model.Booking;
import com.hotel.booking.system.kafka.model.BookingRoomMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingRoomListenerMapper {

    @Mapping(target = "id", source = "bookingId")
    Booking toBooking(BookingRoomMessage bookingRoomMessage);
}
