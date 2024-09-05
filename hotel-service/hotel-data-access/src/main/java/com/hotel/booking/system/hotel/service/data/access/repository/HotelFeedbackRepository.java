package com.hotel.booking.system.hotel.service.data.access.repository;

import com.hotel.booking.system.hotel.service.data.access.entity.HotelFeedbackEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HotelFeedbackRepository extends JpaRepository<HotelFeedbackEntity, UUID> {

    boolean existsByCustomerIdAndHotelId(UUID customerId, UUID hotelId);

    Page<HotelFeedbackEntity> findAllByHotelId(UUID hotelId, Pageable pageable);
}
