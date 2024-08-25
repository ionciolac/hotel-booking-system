package com.hotel.booking.system.hotel.service.ports.out.messaging;

import com.hotel.booking.system.kafka.model.RemoveBookingMessage;

public interface RemovedBookingPublisher {

    void publisher(RemoveBookingMessage removeBookingMessage);
}
