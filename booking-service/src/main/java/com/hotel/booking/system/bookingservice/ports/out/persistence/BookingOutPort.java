package com.hotel.booking.system.bookingservice.ports.out.persistence;

import com.hotel.booking.system.bookingservice.domain.model.Booking;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface BookingOutPort {

    Booking upsertBooking(Booking booking);

    void deleteBooking(UUID id);

    Optional<Booking> getBooking(UUID id);

    Optional<Booking> getBooking(UUID userId, UUID roomId, LocalDateTime fromDate, LocalDateTime toDate);
}
