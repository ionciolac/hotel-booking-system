package com.hotel.booking.system.bookingservice.adapters.out.persistence.mapper;

import com.hotel.booking.system.bookingservice.adapters.out.persistence.enity.BookingEntity;
import com.hotel.booking.system.bookingservice.domain.model.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingPersistenceMapper {

    Booking toBooking(BookingEntity bookingEntity);

    BookingEntity toBookingEntity(Booking booking);
}
