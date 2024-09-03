package com.hotel.booking.system.hotel.service.domain.ports.out.messaging;

import com.hotel.booking.system.kafka.model.BookingMessage;

public interface BookingPublisher {

    void publish(String topic, String key, BookingMessage bookingMessage);
}
