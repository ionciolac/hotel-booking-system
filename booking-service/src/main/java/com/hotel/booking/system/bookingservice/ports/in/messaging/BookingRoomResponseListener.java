package com.hotel.booking.system.bookingservice.ports.in.messaging;

import com.hotel.booking.system.common.common.BookingStatus;

import java.util.UUID;

public interface BookingRoomResponseListener {

    void notifyRoomBookingStatus(UUID id, BookingStatus bookingStatus, UUID roomBookingId);
}
