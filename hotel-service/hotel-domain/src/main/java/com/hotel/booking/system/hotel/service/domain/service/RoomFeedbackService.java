package com.hotel.booking.system.hotel.service.domain.service;

import com.hotel.booking.system.common.common.exception.AlreadyExistException;
import com.hotel.booking.system.common.common.exception.NotFoundException;
import com.hotel.booking.system.hotel.service.domain.model.RoomFeedback;
import com.hotel.booking.system.hotel.service.domain.ports.in.rest.RoomFeedbackInPort;
import com.hotel.booking.system.hotel.service.domain.ports.in.rest.RoomInPort;
import com.hotel.booking.system.hotel.service.domain.ports.out.persistence.RoomFeedbackOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.hotel.booking.system.common.common.utils.AppCommonMessages.*;
import static com.hotel.booking.system.common.common.utils.AppConstants.FEEDBACK_MARK_VALUES;
import static java.lang.String.format;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@Service
public class RoomFeedbackService implements RoomFeedbackInPort {

    // out port
    private final RoomFeedbackOutPort roomFeedbackOutPort;
    // services
    private final RoomInPort roomInPort;

    @Transactional
    @Override
    public RoomFeedback createRoomFeedback(RoomFeedback roomFeedback) {
        var roomId = roomFeedback.getRoom().getId();
        var userId = roomFeedback.getUserId();
        checkIfUserAlreadyAddedFeedback(userId, roomId);
        var room = roomInPort.getRoom(roomId);
        roomFeedback.setRoom(room);
        roomFeedback.generateID();
        return roomFeedbackOutPort.upsertRoomFeedback(roomFeedback);
    }

    @Transactional
    @Override
    public RoomFeedback updateRoomFeedback(RoomFeedback roomFeedback) {
        var id = roomFeedback.getId();
        var dbRoomFeedback = getDBRoomFeedback(id);
        patch(dbRoomFeedback, roomFeedback);
        return roomFeedbackOutPort.upsertRoomFeedback(dbRoomFeedback);
    }

    @Transactional
    @Override
    public void deleteRoomFeedback(UUID id) {
        getDBRoomFeedback(id);
        roomFeedbackOutPort.deleteRoomFeedback(id);
    }

    @Override
    public RoomFeedback getRoomFeedback(UUID id) {
        return getDBRoomFeedback(id);
    }

    @Override
    public Page<RoomFeedback> getRoomFeedback(UUID id, Pageable pageable) {
        return roomFeedbackOutPort.getRoomFeedbackPage(id, pageable);
    }

    private RoomFeedback getDBRoomFeedback(UUID id) {
        var roomFeedback = roomFeedbackOutPort.getRoomFeedback(id);
        if (roomFeedback.isPresent())
            return roomFeedback.get();
        else
            throw new NotFoundException(format(SERVICE_OBJECT_WAS_NOT_FOUND_IN_DB_MESSAGE, FEEDBACK, id));
    }

    private void checkIfUserAlreadyAddedFeedback(UUID userId, UUID roomId) {
        if (roomFeedbackOutPort.hasUserAddedFeedbackToRoom(userId, roomId))
            throw new AlreadyExistException(format(SERVICE_USER_ALREADY_ADDED_FEEDBACK_MESSAGE, userId, ROOM, roomId));
    }

    void patch(RoomFeedback target, RoomFeedback source) {
        if (hasText(source.getUserMessage())) {
            target.setUserMessage(source.getUserMessage());
        }
        if (FEEDBACK_MARK_VALUES.contains(source.getUserMark())) {
            target.setUserMark(source.getUserMark());
        }
    }
}
