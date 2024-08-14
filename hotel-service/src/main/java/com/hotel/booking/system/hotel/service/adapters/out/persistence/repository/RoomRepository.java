package com.hotel.booking.system.hotel.service.adapters.out.persistence.repository;

import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface RoomRepository extends JpaRepository<RoomEntity, UUID>, JpaSpecificationExecutor<RoomEntity> {

    boolean existsByHotelIdAndDoorNumber(UUID hotelId, String doorNumber);
}
