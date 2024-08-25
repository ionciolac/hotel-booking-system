package com.hotel.booking.system.bookingservice.adapters.out.messaging.kafka.mapper;

import com.hotel.booking.system.bookingservice.domain.model.Booking;
import com.hotel.booking.system.kafka.model.BookingRoomMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingRoomPublisherMapper {

    @Mapping(target = "bookingId", source = "id")
    BookingRoomMessage toBookingRoomMessage(Booking booking);
}
