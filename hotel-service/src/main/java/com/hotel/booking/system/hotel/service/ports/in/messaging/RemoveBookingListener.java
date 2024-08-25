package com.hotel.booking.system.hotel.service.ports.in.messaging;

import com.hotel.booking.system.kafka.model.RemoveBookingMessage;

public interface RemoveBookingListener {

    void removeBooking(RemoveBookingMessage removeBookingMessage);
}
