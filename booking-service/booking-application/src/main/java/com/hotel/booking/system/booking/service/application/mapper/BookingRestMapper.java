package com.hotel.booking.system.booking.service.application.mapper;

import com.hotel.booking.system.booking.service.application.data.req.CreateBookingRequest;
import com.hotel.booking.system.booking.service.application.data.req.UpdateBookingRequest;
import com.hotel.booking.system.booking.service.application.data.res.BookingResponse;
import com.hotel.booking.system.booking.service.domain.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingRestMapper {

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "roomBookingId", ignore = true)
    Booking toBooking(CreateBookingRequest createBookingRequest);

    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "roomId", ignore = true)
    @Mapping(target = "roomBookingId", ignore = true)
    @Mapping(target = "hotelId", ignore = true)
    Booking toBooking(UpdateBookingRequest updateBookingRequest);

    BookingResponse toBookingResponse(Booking booking);
}
