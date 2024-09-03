package com.hotel.booking.system.hotel.service.application.adapter;

import com.hotel.booking.system.common.application.data.res.DeletedResponse;
import com.hotel.booking.system.hotel.service.application.data.req.feedback.CreateRoomFeedbackRequest;
import com.hotel.booking.system.hotel.service.application.data.req.feedback.UpdateRoomFeedbackRequest;
import com.hotel.booking.system.hotel.service.application.data.res.feedback.RoomFeedbackResponse;
import com.hotel.booking.system.hotel.service.application.mapper.RoomFeedbackRestMapper;
import com.hotel.booking.system.hotel.service.domain.ports.in.rest.RoomFeedbackInPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.hotel.booking.system.common.common.utils.AppCommonMessages.CONTROLLER_DELETE_MESSAGE_RESPONSE_MESSAGE;
import static com.hotel.booking.system.common.common.utils.AppCommonMessages.FEEDBACK;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RequestMapping("/room-feedback")
@RestController
public class RoomFeedbackRestAdapter {

    private final RoomFeedbackInPort roomFeedbackInPort;
    private final RoomFeedbackRestMapper roomFeedbackRestMapper;

    @PostMapping()
    public ResponseEntity<RoomFeedbackResponse> createRoomFeedback(@RequestBody CreateRoomFeedbackRequest req) {
        var roomFeedback = roomFeedbackInPort.createRoomFeedback(roomFeedbackRestMapper.toRoomFeedback(req));
        return ResponseEntity.status(CREATED).body(roomFeedbackRestMapper.toRoomFeedbackResponse(roomFeedback));
    }

    @PatchMapping()
    public ResponseEntity<RoomFeedbackResponse> updateRoomFeedback(@RequestBody UpdateRoomFeedbackRequest req) {
        var roomFeedback = roomFeedbackInPort.updateRoomFeedback(roomFeedbackRestMapper.toRoomFeedback(req));
        return ResponseEntity.status(CREATED).body(roomFeedbackRestMapper.toRoomFeedbackResponse(roomFeedback));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletedResponse> deleteRoomFeedback(@PathVariable("id") UUID id) {
        roomFeedbackInPort.deleteRoomFeedback(id);
        var msg = format(CONTROLLER_DELETE_MESSAGE_RESPONSE_MESSAGE, FEEDBACK, id);
        return ResponseEntity.status(OK).body(new DeletedResponse(Boolean.TRUE, msg));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomFeedbackResponse> getRoomFeedback(@PathVariable("id") UUID id) {
        var roomFeedback = roomFeedbackInPort.getRoomFeedback(id);
        return ResponseEntity.status(CREATED).body(roomFeedbackRestMapper.toRoomFeedbackResponse(roomFeedback));
    }

    @GetMapping()
    public ResponseEntity<Page<RoomFeedbackResponse>> getRoomFeedback(@RequestParam(name = "room_id") UUID roomId,
                                                                      @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size) {
        var result = roomFeedbackInPort.getRoomFeedback(roomId, PageRequest.of(page, size))
                .map(roomFeedbackRestMapper::toRoomFeedbackResponse);
        return ResponseEntity.status(CREATED).body(result);
    }
}
