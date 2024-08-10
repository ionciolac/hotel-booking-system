package com.hotel.booking.system.hotel.service.adapters.out.persistence.entity;

import com.hotel.booking.system.common.persistence.EntityAuditing;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hotel_address")
@Entity
public class HotelAddressEntity extends EntityAuditing {

    @Id
    private UUID id;
    private String country;
    private String region;
    private String city;
    @Column(name = "postal_code")
    private String postalCode;
    private String street;
    @Column(name = "building_number")
    private String buildingNumber;
}
