package com.hotel.booking.system.bookingservice.ports.in.messaging;

import com.hotel.booking.system.bookingservice.domain.model.Booking;

public interface BookingRoomResponseListener {

    void roomBooked(Booking booking);
}
