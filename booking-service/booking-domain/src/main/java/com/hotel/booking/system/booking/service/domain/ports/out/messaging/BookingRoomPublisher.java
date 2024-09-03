package com.hotel.booking.system.booking.service.domain.ports.out.messaging;

import com.hotel.booking.system.kafka.model.BookingMessage;

public interface BookingRoomPublisher {

    void publish(String topic, String key, BookingMessage bookingMessage);
}
