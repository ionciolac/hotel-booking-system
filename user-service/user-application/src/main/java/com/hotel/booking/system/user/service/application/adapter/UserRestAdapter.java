package com.hotel.booking.system.user.service.application.adapter;

import com.hotel.booking.system.common.application.data.res.DeletedResponse;
import com.hotel.booking.system.user.service.application.data.req.CreateUserRequest;
import com.hotel.booking.system.user.service.application.data.req.UpdateUserRequest;
import com.hotel.booking.system.user.service.application.data.res.UserResponse;
import com.hotel.booking.system.user.service.application.mapper.UserRestAdapterMapper;
import com.hotel.booking.system.user.service.domain.ports.in.UserInPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.hotel.booking.system.common.common.utils.AppCommonMessages.CONTROLLER_DELETE_MESSAGE_RESPONSE_MESSAGE;
import static com.hotel.booking.system.common.common.utils.AppCommonMessages.USER;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RequestMapping("user")
@RestController
public class UserRestAdapter {

    private final UserInPort userInPort;
    private final UserRestAdapterMapper userRestAdapterMapper;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest req) {
        var user = userInPort.createUser(userRestAdapterMapper.toUser(req));
        return ResponseEntity.status(CREATED).body(userRestAdapterMapper.toUserResponse(user));
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpdateUserRequest req) {
        var user = userInPort.updateUser(userRestAdapterMapper.toUser(req));
        return ResponseEntity.status(OK).body(userRestAdapterMapper.toUserResponse(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletedResponse> deleteUser(@PathVariable("id") UUID id) {
        userInPort.deleteUser(id);
        var msg = format(CONTROLLER_DELETE_MESSAGE_RESPONSE_MESSAGE, USER, id);
        return ResponseEntity.status(OK).body(new DeletedResponse(Boolean.TRUE, msg));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") UUID id) {
        var user = userInPort.getUser(id);
        return ResponseEntity.status(OK).body(userRestAdapterMapper.toUserResponse(user));
    }
}
