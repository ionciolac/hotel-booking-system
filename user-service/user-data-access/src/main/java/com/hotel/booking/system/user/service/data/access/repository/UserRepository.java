package com.hotel.booking.system.user.service.data.access.repository;

import com.hotel.booking.system.user.service.data.access.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsByUsernameAndEmailAndPhoneNumber(String username, String email, String phoneNumber);
}
