package com.hotel.booking.system.bookingservice.ports.in.messaging;

import com.hotel.booking.system.bookingservice.domain.model.Booking;

public interface CreatedBookingRoomListener {

    void bookingCreated(Booking booking);
}
