package com.hotel.booking.system.common.data.access;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
@Getter
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
