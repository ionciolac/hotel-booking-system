package com.hotel.booking.system.user.service.adapters.out.persistence.adapter;

import com.hotel.booking.system.user.service.adapters.out.persistence.mapper.UserPersistenceMapper;
import com.hotel.booking.system.user.service.adapters.out.persistence.repository.UserRepository;
import com.hotel.booking.system.user.service.domain.model.User;
import com.hotel.booking.system.user.service.ports.out.UserOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserPersistenceAdapter implements UserOutPort {

    private final UserRepository userRepository;
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
