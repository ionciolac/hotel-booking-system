package com.hotel.booking.system.hotel.service.domain.service;

import com.hotel.booking.system.common.common.RoomType;
import com.hotel.booking.system.common.domain.exception.AlreadyExistException;
import com.hotel.booking.system.common.domain.exception.NotFoundException;
import com.hotel.booking.system.hotel.service.domain.model.Hotel;
import com.hotel.booking.system.hotel.service.domain.model.Room;
import com.hotel.booking.system.hotel.service.ports.in.rest.RoomInPort;
import com.hotel.booking.system.hotel.service.ports.out.HotelOutPort;
import com.hotel.booking.system.hotel.service.ports.out.RoomOutPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class RoomService implements RoomInPort {

    private final RoomOutPort roomOutPort;
    private final HotelOutPort hotelOutPort;

    @Transactional
    @Override
    public Room cretaeRoom(Room room) {
        var hotelId = room.getHotel().getId();
        checkIfRoomAlreadyExistInHotel(hotelId, room.getDoorNumber());
        Hotel hotel = getDBHotel(hotelId);
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
        Hotel hotel = getDBHotel(hotelId);
        return roomOutPort.getRooms(hotel, floor, roomType, pageable);
    }

    void checkIfRoomAlreadyExistInHotel(UUID hotelId, String doorNumber) {
        if (roomOutPort.checkIfRoomAlreadyExistInHotel(hotelId, doorNumber)) {
            String msg = format("Room with door number: %s already exist in hotel: %s", doorNumber, hotelId);
            throw new AlreadyExistException(msg);
        }
    }

    private Hotel getDBHotel(UUID id) {
        Optional<Hotel> hotel = hotelOutPort.getHotel(id);
        if (hotel.isPresent()) {
            return hotel.get();
        } else {
            String msg = format("Hotel with id: %s was not found in DB. Room can't be added.", id);
            throw new NotFoundException(msg);
        }
    }

    private Room getDBRoom(UUID id) {
        Optional<Room> room = roomOutPort.getRoom(id);
        if (room.isPresent()) {
            return room.get();
        } else {
            var msg = format("Room was not found in DB by id: %s.", id);
            throw new NotFoundException(msg);
        }
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
