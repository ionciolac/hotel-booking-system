package com.hotel.booking.system.hotel.service.adapters.out.persistence.entity;

import com.hotel.booking.system.common.common.RoomType;
import com.hotel.booking.system.common.persistence.EntityAuditing;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rooms")
@Entity
public class RoomEntity extends EntityAuditing {

    @Id
    private UUID id;
    @ToString.Exclude
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
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private Set<RoomBookingEntity> roomBookings;
}
