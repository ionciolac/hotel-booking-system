package com.hotel.booking.system.booking.service.data.access.adapter;

import com.hotel.booking.system.booking.service.data.access.mapper.BookingPersistenceMapper;
import com.hotel.booking.system.booking.service.data.access.repository.BookingRepository;
import com.hotel.booking.system.booking.service.domain.model.Booking;
import com.hotel.booking.system.booking.service.domain.ports.out.persistence.BookingOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class BookingPersistenceAdapter implements BookingOutPort {

    // repository
    private final BookingRepository bookingRepository;
    //mapper
    private final BookingPersistenceMapper bookingPersistenceMapper;

    @Override
    public Booking upsertBooking(Booking booking) {
        var bookingEntity = bookingRepository.save(bookingPersistenceMapper.toBookingEntity(booking));
        return bookingPersistenceMapper.toBooking(bookingEntity);
    }

    @Override
    public void deleteBooking(UUID id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public Optional<Booking> getBooking(UUID id) {
        return bookingRepository.findById(id)
                .map(bookingPersistenceMapper::toBooking);
    }

    @Override
    public Optional<Booking> getBooking(UUID userId, UUID roomId, LocalDateTime fromDate, LocalDateTime toDate) {
        return bookingRepository.findByUserIdAndRoomIdAndFromDateAndToDate(userId, roomId, fromDate, toDate)
                .map(bookingPersistenceMapper::toBooking);
    }
}
