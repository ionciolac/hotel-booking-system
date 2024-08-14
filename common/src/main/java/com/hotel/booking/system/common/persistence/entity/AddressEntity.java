package com.hotel.booking.system.common.persistence.entity;

import com.hotel.booking.system.common.persistence.EntityAuditing;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class AddressEntity extends EntityAuditing {

    private String country;
    private String region;
    private String city;
    @Column(name = "postal_code")
    private String postalCode;
    private String street;
    @Column(name = "building_number")
    private String buildingNumber;
}
