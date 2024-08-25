package com.hotel.booking.system.hotel.service.ports.out.messaging;

import com.hotel.booking.system.common.common.BookingStatus;
import com.hotel.booking.system.hotel.service.domain.model.RoomBooking;

public interface CreateBookingPublisher {

    void publisher(RoomBooking roomBooking, BookingStatus status);
}
