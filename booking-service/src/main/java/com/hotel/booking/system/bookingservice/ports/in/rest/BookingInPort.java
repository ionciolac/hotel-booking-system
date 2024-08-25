package com.hotel.booking.system.bookingservice.ports.in.rest;

import com.hotel.booking.system.bookingservice.domain.model.Booking;

import java.util.UUID;

public interface BookingInPort {

    Booking createBooking(Booking booking);

    Booking updateBooking(Booking booking);

    void deleteBooking(UUID id);

    Booking getBooking(UUID id);

    Booking payBooking(UUID id);

    Booking cancelBooking(UUID id);
}
