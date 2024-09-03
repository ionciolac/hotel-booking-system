package com.hotel.booking.system.hotel.service.data.access.repository;

import com.hotel.booking.system.hotel.service.data.access.entity.RoomFeedbackEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomFeedbackRepository extends JpaRepository<RoomFeedbackEntity, UUID> {

    boolean existsByUserIdAndRoomId(UUID userId, UUID roomId);

    Page<RoomFeedbackEntity> findAllByRoomId(UUID roomId, Pageable pageable);
}
