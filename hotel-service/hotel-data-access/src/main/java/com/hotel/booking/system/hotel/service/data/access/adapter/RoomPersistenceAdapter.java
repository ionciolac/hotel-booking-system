package com.hotel.booking.system.hotel.service.data.access.adapter;

import com.hotel.booking.system.common.common.enums.RoomType;
import com.hotel.booking.system.hotel.service.data.access.mapper.HotelPersistenceMapper;
import com.hotel.booking.system.hotel.service.data.access.mapper.RoomPersistenceMapper;
import com.hotel.booking.system.hotel.service.data.access.mapper.RoomWithHotelPersistenceMapper;
import com.hotel.booking.system.hotel.service.data.access.repository.RoomRepository;
import com.hotel.booking.system.hotel.service.domain.model.Hotel;
import com.hotel.booking.system.hotel.service.domain.model.Room;
import com.hotel.booking.system.hotel.service.domain.ports.out.persistence.RoomOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.hotel.booking.system.hotel.service.data.access.specification.RoomEntitySpecification.*;

@RequiredArgsConstructor
@Component
public class RoomPersistenceAdapter implements RoomOutPort {

    // repository
    private final RoomRepository roomRepository;
    // mappers
    private final RoomPersistenceMapper roomPersistenceMapper;
    private final RoomWithHotelPersistenceMapper roomWithHotelPersistenceMapper;
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
        var hotelEntity = hotelPersistenceMapper.toHotelEntity(hotel);
        var specification = Specification
                .where(hotel == null ? null : hotelIdFilter(hotelEntity))
                .and(floor == null ? null : floorFilter(floor))
                .and(roomType == null ? null : roomTypeFilter(roomType));
        return roomRepository.findAll(specification, pageable).map(roomPersistenceMapper::toRoom);
    }

    @Override
    public Page<Room> getRooms(String country, String city, LocalDateTime fromDate, LocalDateTime toDate,
                               Double minPricePerNight, Double maxPricePerNight, Pageable pageable) {
        var specification = Specification
                .where(filterByIsRoomAvailable(Boolean.TRUE))
                .and(country == null ? null : filterByCountry(country))
                .and(city == null ? null : filterByCity(city))
                .and(fromDate == null ? null : filterByFromDate(fromDate))
                .and(toDate == null ? null : filterByToDate(toDate))
                .and(minPricePerNight == null ? null : filterByMinPricePerNight(minPricePerNight))
                .and(maxPricePerNight == null ? null : filterByMaxPricePerNight(maxPricePerNight));
        return roomRepository.findAll(specification, pageable).map(roomWithHotelPersistenceMapper::toRoom);
    }
}
