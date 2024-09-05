package com.hotel.booking.system.booking.service.domain.ports.out.persistence;

import com.hotel.booking.system.booking.service.domain.model.Booking;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface BookingOutPort {

    Booking upsertBooking(Booking booking);

    void deleteBooking(UUID id);

    Optional<Booking> getBooking(UUID id);

    Optional<Booking> getBooking(UUID customerId, UUID roomId, LocalDateTime fromDate, LocalDateTime toDate);
}
