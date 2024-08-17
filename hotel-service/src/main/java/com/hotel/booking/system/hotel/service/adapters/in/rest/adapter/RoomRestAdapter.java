package com.hotel.booking.system.hotel.service.adapters.in.rest.adapter;

import com.hotel.booking.system.common.common.RoomType;
import com.hotel.booking.system.common.rest.data.res.DeletedResponse;
import com.hotel.booking.system.hotel.service.adapters.in.rest.data.req.room.CreateRoomRequest;
import com.hotel.booking.system.hotel.service.adapters.in.rest.data.req.room.UpdateRoomRequest;
import com.hotel.booking.system.hotel.service.adapters.in.rest.data.res.room.RoomResponse;
import com.hotel.booking.system.hotel.service.adapters.in.rest.mapper.RoomRestMapper;
import com.hotel.booking.system.hotel.service.ports.in.rest.RoomInPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.hotel.booking.system.common.domain.utils.AppCommonMessages.CONTROLLER_DELETE_MESSAGE_RESPONSE_MESSAGE;
import static com.hotel.booking.system.common.domain.utils.AppCommonMessages.ROOM;
import static java.lang.String.format;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RequestMapping("/room")
@RestController
public class RoomRestAdapter {

    private final RoomInPort roomInPort;
    private final RoomRestMapper roomRestMapper;

    @PostMapping
    public ResponseEntity<RoomResponse> createRoom(@RequestBody CreateRoomRequest req) {
        var room = roomInPort.cretaeRoom(roomRestMapper.toRoom(req));
        return ResponseEntity.status(CREATED).body(roomRestMapper.toRoomResponse(room));
    }

    @PatchMapping
    public ResponseEntity<RoomResponse> updateRoom(@RequestBody UpdateRoomRequest req) {
        var room = roomInPort.updateRoom(roomRestMapper.toRoom(req));
        return ResponseEntity.status(OK).body(roomRestMapper.toRoomResponse(room));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletedResponse> deleteRoom(@PathVariable("id") UUID id) {
        roomInPort.deleteRoom(id);
        var msg = format(CONTROLLER_DELETE_MESSAGE_RESPONSE_MESSAGE, ROOM, id);
        return ResponseEntity.status(OK).body(new DeletedResponse(Boolean.TRUE, msg));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoom(@PathVariable("id") UUID id) {
        var room = roomInPort.getRoom(id);
        return ResponseEntity.status(OK).body(roomRestMapper.toRoomResponse(room));
    }

    @GetMapping
    public ResponseEntity<Page<RoomResponse>> getRoom(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size,
                                                      @RequestParam(value = "hotel_id") UUID hotelId,
                                                      @RequestParam(value = "floor", required = false) Integer floor,
                                                      @RequestParam(value = "room_type", required = false) RoomType roomType) {
        var result = roomInPort.getRooms(hotelId, floor, roomType, of(page, size)).map(roomRestMapper::toRoomResponse);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
