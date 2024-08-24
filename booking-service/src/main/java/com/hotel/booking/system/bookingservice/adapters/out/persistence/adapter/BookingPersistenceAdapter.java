package com.hotel.booking.system.bookingservice.adapters.out.persistence.adapter;

import com.hotel.booking.system.bookingservice.adapters.out.persistence.mapper.BookingPersistenceMapper;
import com.hotel.booking.system.bookingservice.adapters.out.persistence.repository.BookingRepository;
import com.hotel.booking.system.bookingservice.domain.model.Booking;
import com.hotel.booking.system.bookingservice.ports.out.persistence.BookingOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookingPersistenceAdapter implements BookingOutPort {

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
