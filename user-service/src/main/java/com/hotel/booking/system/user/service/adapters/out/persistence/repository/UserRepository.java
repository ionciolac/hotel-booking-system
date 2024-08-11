package com.hotel.booking.system.user.service.adapters.out.persistence.repository;

import com.hotel.booking.system.user.service.adapters.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsByUsernameAndEmailAndPhoneNumber(String username, String email, String phoneNumber);
}
