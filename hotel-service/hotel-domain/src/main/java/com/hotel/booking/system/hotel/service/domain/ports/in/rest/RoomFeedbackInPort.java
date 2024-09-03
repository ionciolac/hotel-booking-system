package com.hotel.booking.system.hotel.service.domain.ports.in.rest;

import com.hotel.booking.system.hotel.service.domain.model.RoomFeedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RoomFeedbackInPort {

    RoomFeedback createRoomFeedback(RoomFeedback roomFeedback);

    RoomFeedback updateRoomFeedback(RoomFeedback roomFeedback);

    void deleteRoomFeedback(UUID id);

    RoomFeedback getRoomFeedback(UUID id);

    Page<RoomFeedback> getRoomFeedback(UUID id, Pageable pageable);
}
