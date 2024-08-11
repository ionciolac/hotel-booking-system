package com.hotel.booking.system.user.service.ports.in;

import com.hotel.booking.system.user.service.domain.model.User;

import java.util.UUID;

public interface UserInPort {

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(UUID id);

    User getUser(UUID id);
}
