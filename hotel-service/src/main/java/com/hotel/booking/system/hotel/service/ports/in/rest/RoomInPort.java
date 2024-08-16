package com.hotel.booking.system.hotel.service.ports.in.rest;

import com.hotel.booking.system.common.common.RoomType;
import com.hotel.booking.system.hotel.service.domain.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RoomInPort {

    Room cretaeRoom(Room room);

    Room updateRoom(Room room);

    void deleteRoom(UUID id);

    Room getRoom(UUID id);

    Page<Room> getRooms(UUID hotelId, Integer floor, RoomType roomType, Pageable pageable);
}
