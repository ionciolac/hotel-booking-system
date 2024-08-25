package com.hotel.booking.system.bookingservice.ports.in.messaging;

import com.hotel.booking.system.kafka.model.RemoveBookingMessage;

public interface RemovedBookingRoomListener {

    void bookingRemoved(RemoveBookingMessage removeBookingMessage);
}
