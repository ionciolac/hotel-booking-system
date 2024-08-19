package com.hotel.booking.system.hotel.service.adapters.out.persistence.entity;

import com.hotel.booking.system.common.common.RoomType;
import com.hotel.booking.system.common.persistence.EntityAuditing;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.UUID;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rooms")
@Entity
public class RoomEntity extends EntityAuditing {

    @Id
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "hotels_id", referencedColumnName = "id")
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
