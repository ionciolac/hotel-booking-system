package com.hotel.booking.system.user.service.domain.service;

import com.hotel.booking.system.common.domain.AlreadyExistException;
import com.hotel.booking.system.common.domain.NotFoundException;
import com.hotel.booking.system.user.service.domain.model.User;
import com.hotel.booking.system.user.service.ports.in.UserInPort;
import com.hotel.booking.system.user.service.ports.out.UserOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class UserService implements UserInPort {

    private final UserOutPort userOutPort;

    @Override
    public User createUser(User user) {
        checkIfUserIsUnique(user);
        var userAddress = user.getAddress();
        userAddress.generateUUID();
        user.generateUUID();
        user.setAddress(userAddress);
        return userOutPort.upserUser(user);
    }

    @Override
    public User updateUser(User user) {
        var userId = user.getId();
        var dbUser = getDBUser(userId);
        var userAddressId = dbUser.getAddress().getId();
        var userAddress = user.getAddress();
        userAddress.setId(userAddressId);
        user.setAddress(userAddress);
        return userOutPort.upserUser(user);
    }

    @Override
    public void deleteUser(UUID id) {
        getDBUser(id);
        userOutPort.deleteUser(id);
    }

    @Override
    public User getUser(UUID id) {
        return getDBUser(id);
    }

    private User getDBUser(UUID id) {
        Optional<User> user = userOutPort.getUser(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            var msg = format("User was not found in DB by id: %s.", id);
            throw new NotFoundException(msg);
        }
    }

    private void checkIfUserIsUnique(User user) {
        if (userOutPort.checkIFUserExist(user))
            throw new AlreadyExistException("User with same username, phoneNumber and email already exist in DB.");
    }
}
