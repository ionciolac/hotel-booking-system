package com.hotel.booking.system.hotel.service.ports.out;

import com.hotel.booking.system.common.common.RoomType;
import com.hotel.booking.system.hotel.service.domain.model.Hotel;
import com.hotel.booking.system.hotel.service.domain.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface RoomOutPort {

    Room upsertRoom(Room room);

    void deleteRoom(UUID id);

    Optional<Room> getRoom(UUID id);

    boolean checkIfRoomAlreadyExistInHotel(UUID hotelId, String doorNumber);

    Page<Room> getRooms(Hotel hotel, Integer floor, RoomType roomType, Pageable pageable);

    Page<Room> getRooms(String country, String city, LocalDateTime fromDate, LocalDateTime toDate,
                        Double minPricePerNight, Double maxPricePerNight, Pageable pageable);
}
