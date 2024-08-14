package com.hotel.booking.system.hotel.service.adapters.out.persistence.adapter;

import com.hotel.booking.system.common.common.RoomType;
import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.HotelEntity;
import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.RoomEntity;
import com.hotel.booking.system.hotel.service.adapters.out.persistence.mapper.HotelPersistenceMapper;
import com.hotel.booking.system.hotel.service.adapters.out.persistence.mapper.RoomPersistenceMapper;
import com.hotel.booking.system.hotel.service.adapters.out.persistence.repository.RoomRepository;
import com.hotel.booking.system.hotel.service.domain.model.Hotel;
import com.hotel.booking.system.hotel.service.domain.model.Room;
import com.hotel.booking.system.hotel.service.ports.out.RoomOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.hotel.booking.system.hotel.service.adapters.out.persistence.specification.RoomEntitySpecification.*;

@RequiredArgsConstructor
@Service
public class RoomPersistenceAdapter implements RoomOutPort {

    private final RoomRepository roomRepository;
    private final RoomPersistenceMapper roomPersistenceMapper;
    private final HotelPersistenceMapper hotelPersistenceMapper;

    @Override
    public Room upsertRoom(Room room) {
        var roomEntity = roomRepository.save(roomPersistenceMapper.toRoomEntity(room));
        return roomPersistenceMapper.toRoom(roomEntity);
    }

    @Override
    public void deleteRoom(UUID id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Optional<Room> getRoom(UUID id) {
        return roomRepository.findById(id).map(roomPersistenceMapper::toRoom);
    }

    @Override
    public boolean checkIfRoomAlreadyExistInHotel(UUID hotelId, String doorNumber) {
        return roomRepository.existsByHotelIdAndDoorNumber(hotelId, doorNumber);
    }

    @Override
    public Page<Room> getRooms(Hotel hotel, Integer floor, RoomType roomType, Pageable pageable) {
        HotelEntity hotelEntity = hotelPersistenceMapper.toHotelEntity(hotel);
        Specification<RoomEntity> specification = Specification
                .where(hotel == null ? null : hotelIdFilter(hotelEntity))
                .and(floor == null ? null : floorFilter(floor))
                .and(roomType == null ? null : roomTypeFilter(roomType));
        return roomRepository.findAll(specification, pageable).map(roomPersistenceMapper::toRoom);
    }
}
