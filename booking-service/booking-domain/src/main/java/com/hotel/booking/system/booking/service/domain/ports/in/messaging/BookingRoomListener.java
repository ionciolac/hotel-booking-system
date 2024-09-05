package com.hotel.booking.system.booking.service.domain.ports.in.messaging;

import com.hotel.booking.system.kafka.model.BookingMessage;

public interface BookingRoomListener {

    void bookingConsumer(BookingMessage bookingMessage);
}
