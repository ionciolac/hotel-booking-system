package com.hotel.booking.system.hotel.service.ports.in.rest;

import java.time.LocalDateTime;
import java.util.UUID;

public interface RoomBookingInPort {

    boolean checkIfRoomIsBooked(UUID roomId, LocalDateTime fromDate, LocalDateTime toDate);
}
