package com.hotel.booking.system.hotel.service.ports.in.messaging;

import com.hotel.booking.system.hotel.service.domain.model.RoomBooking;

public interface BookRoomListener {

    void bookRoom(RoomBooking roomBooking);
}
