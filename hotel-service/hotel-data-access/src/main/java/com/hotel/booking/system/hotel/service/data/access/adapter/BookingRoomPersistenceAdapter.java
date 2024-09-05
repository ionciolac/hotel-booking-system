package com.hotel.booking.system.hotel.service.data.access.adapter;

import com.hotel.booking.system.hotel.service.data.access.mapper.BookingRoomPersistenceMapper;
import com.hotel.booking.system.hotel.service.data.access.repository.RoomBookingRepository;
import com.hotel.booking.system.hotel.service.domain.model.RoomBooking;
import com.hotel.booking.system.hotel.service.domain.ports.out.persistence.BookingRoomOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.hotel.booking.system.hotel.service.data.access.specification.RoomBookingEntitySpecification.*;

@RequiredArgsConstructor
@Component
public class BookingRoomPersistenceAdapter implements BookingRoomOutPort {

    // repository
    private final RoomBookingRepository roomBookingRepository;
    // mappers
    private final BookingRoomPersistenceMapper bookingRoomPersistenceMapper;

    @Override
    public RoomBooking upsertRoomBooking(RoomBooking roomBooking) {
        var roomBookingEntity = roomBookingRepository
                .save(bookingRoomPersistenceMapper.toRoomBookingEntity(roomBooking));
        return bookingRoomPersistenceMapper.toRoomBooking(roomBookingEntity);
    }

    @Override
    public boolean checkIfRoomIsBooked(UUID roomId, UUID customerId, LocalDateTime fromDate, LocalDateTime toDate) {
        var specification = Specification
                .where(roomIdFilter(roomId))
                .and(customerIdFilter(customerId))
                .and(fromDateToDateFilter(fromDate, toDate));
        return !roomBookingRepository.findAll(specification).isEmpty();
    }

    @Override
    public Optional<RoomBooking> getRoomBooking(UUID id) {
        return roomBookingRepository.findById(id)
                .map(bookingRoomPersistenceMapper::toRoomBooking);
    }

    @Override
    public void removeRoomBooking(UUID id) {
        roomBookingRepository.deleteById(id);
    }
}
