package com.hotel.booking.system.hotel.service.adapters.out.persistence.entity;

import com.hotel.booking.system.common.common.RoomType;
import com.hotel.booking.system.common.persistence.EntityAuditing;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rooms")
@Entity
public class RoomEntity extends EntityAuditing {

    @Id
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    private int floor;
    private String doorNumber;
    private Boolean isRoomAvailable;
    private double pricePerNight;
    private String currency;
}
