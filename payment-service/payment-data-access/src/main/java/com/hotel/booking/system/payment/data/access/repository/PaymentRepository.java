package com.hotel.booking.system.payment.data.access.repository;

import com.hotel.booking.system.payment.data.access.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID> {
}
