package com.hotel.booking.system.bookingservice.ports.out.messaging;

import com.hotel.booking.system.kafka.model.RemoveBookingMessage;

public interface RemoveBookingRoomPublisher {

    void publish(RemoveBookingMessage removeBookingMessage);
}
