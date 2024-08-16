package com.hotel.booking.system.common.domain.model;

import lombok.*;

import java.util.UUID;

import static java.util.UUID.randomUUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private UUID id;
    private String country;
    private String region;
    private String city;
    private String postalCode;
    private String street;
    private String buildingNumber;

    public void generateID() {
        this.id = randomUUID();
    }
}
