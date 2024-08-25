package com.hotel.booking.system.bookingservice.ports.out.messaging;


import com.hotel.booking.system.bookingservice.domain.model.Booking;

public interface CreateBookingRoomPublisher {

    void publish(Booking booking);
}
