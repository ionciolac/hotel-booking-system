package com.hotel.booking.system.hotel.service.data.access.adapter;

import com.hotel.booking.system.hotel.service.data.access.mapper.RoomFeedbackPersistenceMapper;
import com.hotel.booking.system.hotel.service.data.access.repository.RoomFeedbackRepository;
import com.hotel.booking.system.hotel.service.domain.model.RoomFeedback;
import com.hotel.booking.system.hotel.service.domain.ports.out.persistence.RoomFeedbackOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class RoomFeedbackPersistenceAdapter implements RoomFeedbackOutPort {

    private final RoomFeedbackRepository roomFeedbackRepository;
    private final RoomFeedbackPersistenceMapper roomFeedbackPersistenceMapper;

    @Override
    public RoomFeedback upsertRoomFeedback(RoomFeedback roomFeedback) {
        var roomFeedbackEntity = roomFeedbackPersistenceMapper.toRoomFeedbackEntity(roomFeedback);
        roomFeedbackEntity = roomFeedbackRepository.save(roomFeedbackEntity);
        return roomFeedbackPersistenceMapper.toRoomFeedback(roomFeedbackEntity);
    }

    @Override
    public void deleteRoomFeedback(UUID id) {
        roomFeedbackRepository.deleteById(id);
    }

    @Override
    public Optional<RoomFeedback> getRoomFeedback(UUID id) {
        return roomFeedbackRepository.findById(id).map(roomFeedbackPersistenceMapper::toRoomFeedback);
    }

    @Override
    public boolean hasUserAddedFeedbackToRoom(UUID userId, UUID roomId) {
        return roomFeedbackRepository.existsByUserIdAndRoomId(userId, roomId);
    }

    @Override
    public Page<RoomFeedback> getRoomFeedbackPage(UUID roomId, Pageable pageable) {
        return roomFeedbackRepository.findAllByRoomId(roomId, pageable)
                .map(roomFeedbackPersistenceMapper::toRoomFeedback);
    }
}
