package com.hotel.booking.system.hotel.service.domain.ports.in.messaging;

import com.hotel.booking.system.kafka.model.BookingMessage;

public interface BookingListener {

    void bookingConsumer(BookingMessage bookingMessage);
}
