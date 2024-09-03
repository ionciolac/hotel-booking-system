package com.hotel.booking.system.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseId {

    private String country;
    private String region;
    private String city;
    private String postalCode;
    private String street;
    private String buildingNumber;
}
