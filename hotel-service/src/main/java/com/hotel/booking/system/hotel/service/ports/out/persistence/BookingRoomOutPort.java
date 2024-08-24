package com.hotel.booking.system.hotel.service.ports.out.persistence;

import com.hotel.booking.system.hotel.service.domain.model.RoomBooking;

import java.time.LocalDateTime;
import java.util.UUID;

public interface BookingRoomOutPort {

    RoomBooking insertRoomBooking(RoomBooking roomBooking);

    boolean checkIfRoomIsBooked(UUID roomId, LocalDateTime fromDate, LocalDateTime toDate);
}
