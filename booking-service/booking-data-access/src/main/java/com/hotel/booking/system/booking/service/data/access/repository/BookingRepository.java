package com.hotel.booking.system.booking.service.data.access.repository;

import com.hotel.booking.system.booking.service.data.access.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {

    Optional<BookingEntity> findByCustomerIdAndRoomIdAndFromDateAndToDate(UUID customerId, UUID roomId, LocalDateTime fromDate, LocalDateTime toDate);
}
