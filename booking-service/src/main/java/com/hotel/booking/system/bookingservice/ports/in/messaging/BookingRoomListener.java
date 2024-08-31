package com.hotel.booking.system.bookingservice.ports.in.messaging;

import com.hotel.booking.system.kafka.model.BookingMessage;

public interface BookingRoomListener {

    void consumer(BookingMessage bookingMessage);
}
