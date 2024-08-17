package com.hotel.booking.system.hotel.service.domain.model;

import com.hotel.booking.system.common.common.Stars;
import com.hotel.booking.system.common.domain.model.Address;
import lombok.*;

import java.util.UUID;

import static java.util.UUID.randomUUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private UUID id;
    private String name;
    private Stars stars;
    private Address address;
    private Double minPricePerNight;
    private Double maxPricePerNight;
    private String currency;
    private int checkinHour;
    private int checkoutHour;

    public void generateID() {
        this.id = randomUUID();
    }
}
