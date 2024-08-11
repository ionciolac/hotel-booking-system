package com.hotel.booking.system.user.service.ports.out;

import com.hotel.booking.system.user.service.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserOutPort {

    User upserUser(User user);

    void deleteUser(UUID id);

    Optional<User> getUser(UUID id);

    boolean checkIFUserExist(User user);
}
