package com.hotel.booking.system.hotel.service.domain.model;

import com.hotel.booking.system.common.common.Stars;
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
public class Hotel {

    private UUID id;
    private String name;
    private Stars stars;
    private HotelAddress address;
    private Double minPricePerNight;
    private Double maxPricePerNight;
    private String currency;

    public void generateID() {
        this.id = randomUUID();
    }
}
