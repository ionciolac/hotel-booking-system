package com.hotel.booking.system.bookingservice.adapters.out.persistence.repository;

import com.hotel.booking.system.bookingservice.adapters.out.persistence.enity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {

    Optional<BookingEntity> findByUserIdAndRoomIdAndFromDateAndToDate(UUID userId, UUID roomId, LocalDateTime fromDate, LocalDateTime toDate);
}
