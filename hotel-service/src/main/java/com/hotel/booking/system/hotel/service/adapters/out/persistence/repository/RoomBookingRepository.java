package com.hotel.booking.system.hotel.service.adapters.out.persistence.repository;

import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.RoomBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBookingEntity, UUID> {

    @Query(nativeQuery = true, value = """ 
            SELECT rbe1_0.id FROM room_bookings rbe1_0
            WHERE rbe1_0.room_id = :roomId
              AND rbe1_0.from_date <= to_timestamp(:toDate, 'YYYY-MM-DDThh24:MI')
              AND rbe1_0.to_date >= to_timestamp(:fromDate, 'YYYY-MM-DDThh24:MI') FETCH FIRST 1 ROWS ONLY
              """)
    Optional<String> findByRoomIdAndFromDateTimeToDateTime(UUID roomId, String fromDate, String toDate);
}
