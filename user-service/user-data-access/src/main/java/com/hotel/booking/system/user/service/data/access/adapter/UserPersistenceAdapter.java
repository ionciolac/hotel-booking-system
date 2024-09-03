package com.hotel.booking.system.user.service.data.access.adapter;

import com.hotel.booking.system.user.service.data.access.mapper.UserPersistenceMapper;
import com.hotel.booking.system.user.service.data.access.repository.UserRepository;
import com.hotel.booking.system.user.service.domain.model.User;
import com.hotel.booking.system.user.service.domain.ports.out.UserOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserPersistenceAdapter implements UserOutPort {

    // repository
    private final UserRepository userRepository;
    // mappers
    private final UserPersistenceMapper userPersistenceMapper;

    @Override
    public User upserUser(User user) {
        var userEntity = userPersistenceMapper.toUserEntity(user);
        return userPersistenceMapper.toUser(userRepository.save(userEntity));
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> getUser(UUID id) {
        return userRepository.findById(id).map(userPersistenceMapper::toUser);
    }

    @Override
    public boolean checkIFUserExist(User user) {
        return userRepository
                .existsByUsernameAndEmailAndPhoneNumber(user.getUsername(), user.getEmail(), user.getPhoneNumber());
    }
}
