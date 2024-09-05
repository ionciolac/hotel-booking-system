package com.hotel.booking.system.customer.service.data.access.repository;

import com.hotel.booking.system.customer.service.data.access.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {

    boolean existsByUsernameAndEmailAndPhoneNumber(String username, String email, String phoneNumber);
}
