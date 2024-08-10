package com.hotel.booking.system.hotel.service.adapters.out.persistence.repository;

import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.FeedbackEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity, UUID> {

    boolean existsByUserIdAndHotelId(UUID userId, UUID hotelId);

    Page<FeedbackEntity> findAllByHotelId(UUID hotelId, Pageable pageable);
}
