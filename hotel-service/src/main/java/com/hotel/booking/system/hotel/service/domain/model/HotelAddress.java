package com.hotel.booking.system.hotel.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static java.util.UUID.randomUUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelAddress {

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
