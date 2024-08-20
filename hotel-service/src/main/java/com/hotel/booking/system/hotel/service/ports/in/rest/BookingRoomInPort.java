package com.hotel.booking.system.hotel.service.ports.in.rest;

import com.hotel.booking.system.hotel.service.domain.model.AvailableRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface BookingRoomInPort {

    boolean checkIfRoomIsBooked(UUID roomId, LocalDateTime fromDate, LocalDateTime toDate);

    Page<AvailableRoom> getAvailableRooms(String country, String city, LocalDateTime fromDate, LocalDateTime toDate,
                                          Double minPricePerNight, Double maxPricePerNight, Pageable pageable);
}
