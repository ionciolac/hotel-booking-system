package com.hotel.booking.system.hotel.service.domain.service;

import com.hotel.booking.system.common.common.enums.RoomType;
import com.hotel.booking.system.common.common.exception.AlreadyExistException;
import com.hotel.booking.system.common.common.exception.NotFoundException;
import com.hotel.booking.system.hotel.service.domain.model.Hotel;
import com.hotel.booking.system.hotel.service.domain.model.Room;
import com.hotel.booking.system.hotel.service.domain.ports.in.rest.HotelInPort;
import com.hotel.booking.system.hotel.service.domain.ports.in.rest.RoomInPort;
import com.hotel.booking.system.hotel.service.domain.ports.out.persistence.RoomOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.hotel.booking.system.common.common.utils.AppCommonMessages.*;
import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class RoomService implements RoomInPort {

    private final RoomOutPort roomOutPort;
    // services
    private final HotelInPort hotelInPort;

    @Transactional
    @Override
    public Room cretaeRoom(Room room) {
        var hotelId = room.getHotel().getId();
        checkIfRoomAlreadyExistInHotel(hotelId, room.getDoorNumber());
        Hotel hotel = hotelInPort.getHotel(hotelId);
        room.setHotel(hotel);
        room.setCurrency(hotel.getCurrency());
        room.generateID();
        return roomOutPort.upsertRoom(room);
    }

    @Transactional
    @Override
    public Room updateRoom(Room room) {
        Room dbRoom = getDBRoom(room.getId());
        patch(dbRoom, room);
        return roomOutPort.upsertRoom(dbRoom);
    }

    @Transactional
    @Override
    public void deleteRoom(UUID id) {
        getDBRoom(id);
        roomOutPort.deleteRoom(id);
    }

    @Transactional
    @Override
    public Room getRoom(UUID id) {
        return getDBRoom(id);
    }

    @Transactional
    @Override
    public Page<Room> getRooms(UUID hotelId, Integer floor, RoomType roomType, Pageable pageable) {
        Hotel hotel = hotelInPort.getHotel(hotelId);
        return roomOutPort.getRooms(hotel, floor, roomType, pageable);
    }

    @Override
    public Page<Room> getRooms(String country, String city, LocalDateTime fromDate, LocalDateTime toDate,
                               Double minPricePerNight, Double maxPricePerNight, Pageable pageable) {
        return roomOutPort.getRooms(country, city, fromDate, toDate, minPricePerNight, maxPricePerNight, pageable);
    }

    void checkIfRoomAlreadyExistInHotel(UUID hotelId, String doorNumber) {
        if (roomOutPort.checkIfRoomAlreadyExistInHotel(hotelId, doorNumber))
            throw new AlreadyExistException(format(SERVICE_ROOM_ALREADY_EXIST_MESSAGE, doorNumber, hotelId));
    }

    private Room getDBRoom(UUID id) {
        var room = roomOutPort.getRoom(id);
        if (room.isPresent())
            return room.get();
        else
            throw new NotFoundException(format(SERVICE_OBJECT_WAS_NOT_FOUND_IN_DB_MESSAGE, ROOM, id));
    }

    private void patch(Room target, Room source) {
        if (source.getIsRoomAvailable() != null) {
            target.setIsRoomAvailable(source.getIsRoomAvailable());
        }
        if (source.getPricePerNight() != 0) {
            target.setPricePerNight(source.getPricePerNight());
        }
    }
}
