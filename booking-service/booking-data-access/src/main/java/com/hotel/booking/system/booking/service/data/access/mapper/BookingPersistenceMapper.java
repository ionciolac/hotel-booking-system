package com.hotel.booking.system.booking.service.data.access.mapper;

import com.hotel.booking.system.booking.service.data.access.entity.BookingEntity;
import com.hotel.booking.system.booking.service.domain.model.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingPersistenceMapper {

    Booking toBooking(BookingEntity bookingEntity);

    BookingEntity toBookingEntity(Booking booking);
}
