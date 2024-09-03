package com.hotel.booking.system.hotel.service.data.access.entity;

import com.hotel.booking.system.common.common.enums.Stars;
import com.hotel.booking.system.common.data.access.EntityAuditing;
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
@Table(name = "hotels")
@Entity
public class HotelEntity extends EntityAuditing {

    @Id
    private UUID id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Stars stars;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_address_id", referencedColumnName = "id")
    private HotelAddressEntity address;
    private Double minPricePerNight;
    private Double maxPricePerNight;
    private String currency;
    private int checkinHour;
    private int checkoutHour;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    private Set<RoomEntity> rooms;
}
