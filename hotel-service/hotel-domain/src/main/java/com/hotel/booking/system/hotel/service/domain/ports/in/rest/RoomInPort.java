package com.hotel.booking.system.hotel.service.domain.ports.in.rest;

import com.hotel.booking.system.common.common.enums.RoomType;
import com.hotel.booking.system.hotel.service.domain.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface RoomInPort {

    Room cretaeRoom(Room room);

    Room updateRoom(Room room);

    void deleteRoom(UUID id);

    Room getRoom(UUID id);

    Page<Room> getRooms(UUID hotelId, Integer floor, RoomType roomType, Pageable pageable);

    Page<Room> getRooms(String country, String city, LocalDateTime fromDate, LocalDateTime toDate,
                        Double minPricePerNight, Double maxPricePerNight, Pageable pageable);
}
