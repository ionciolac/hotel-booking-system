package com.hotel.booking.system.hotel.service.adapters.out.persistence.entity;

import com.hotel.booking.system.common.common.Stars;
import com.hotel.booking.system.common.persistence.EntityAuditing;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hotel")
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
}
