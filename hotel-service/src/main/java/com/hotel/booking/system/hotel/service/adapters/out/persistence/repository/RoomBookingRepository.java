package com.hotel.booking.system.hotel.service.adapters.out.persistence.repository;

import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.RoomBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBookingEntity, UUID>, JpaSpecificationExecutor<RoomBookingEntity> {
}
