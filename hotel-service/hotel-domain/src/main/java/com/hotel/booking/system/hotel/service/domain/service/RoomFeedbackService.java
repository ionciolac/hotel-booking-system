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
import static java.lang.String.format;

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
        var customerId = roomFeedback.getCustomerId();
        checkIfCustomerAlreadyAddedFeedback(customerId, roomId);
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
        dbRoomFeedback.patch(dbRoomFeedback, roomFeedback);
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

    private void checkIfCustomerAlreadyAddedFeedback(UUID customerId, UUID roomId) {
        if (roomFeedbackOutPort.hasCustomerAddedFeedbackToRoom(customerId, roomId))
            throw new AlreadyExistException(format(SERVICE_CUSTOMER_ALREADY_ADDED_FEEDBACK_MESSAGE, customerId, ROOM, roomId));
    }
}
