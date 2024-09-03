package com.hotel.booking.system.hotel.service.data.access.repository;

import com.hotel.booking.system.hotel.service.data.access.entity.RoomBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface RoomBookingRepository extends JpaRepository<RoomBookingEntity, UUID>, JpaSpecificationExecutor<RoomBookingEntity> {
}
