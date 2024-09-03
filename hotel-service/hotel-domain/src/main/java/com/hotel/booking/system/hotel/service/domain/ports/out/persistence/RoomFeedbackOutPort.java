package com.hotel.booking.system.hotel.service.domain.ports.out.persistence;

import com.hotel.booking.system.hotel.service.domain.model.RoomFeedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface RoomFeedbackOutPort {

    RoomFeedback upsertRoomFeedback(RoomFeedback roomFeedback);

    void deleteRoomFeedback(UUID id);

    Optional<RoomFeedback> getRoomFeedback(UUID id);

    boolean hasUserAddedFeedbackToRoom(UUID userId, UUID roomId);

    Page<RoomFeedback> getRoomFeedbackPage(UUID roomId, Pageable pageable);
}
