package com.hotel.booking.system.user.service.adapters.in.rest.adapter;

import com.hotel.booking.system.common.rest.data.DeletedResponse;
import com.hotel.booking.system.user.service.adapters.in.rest.data.req.CreateUserRequest;
import com.hotel.booking.system.user.service.adapters.in.rest.data.req.UpdateUserRequest;
import com.hotel.booking.system.user.service.adapters.in.rest.data.res.UserResponse;
import com.hotel.booking.system.user.service.adapters.in.rest.mapper.UserRestAdapterMapper;
import com.hotel.booking.system.user.service.ports.in.UserInPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
        var msg = String.format("User with ID: %s was deleted", id);
        return ResponseEntity.status(OK).body(new DeletedResponse(Boolean.TRUE, msg));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") UUID id) {
        var user = userInPort.getUser(id);
        return ResponseEntity.status(OK).body(userRestAdapterMapper.toUserResponse(user));
    }
}
