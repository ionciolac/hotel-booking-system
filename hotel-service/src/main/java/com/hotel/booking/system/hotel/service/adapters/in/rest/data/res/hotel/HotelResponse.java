package com.hotel.booking.system.hotel.service.adapters.in.rest.data.res.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel.booking.system.common.common.Stars;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponse {

    private UUID id;
    private String name;
    private Stars stars;
    private HotelAddressResponse address;
    @JsonProperty("min_price_per_night")
    private Double minPricePerNight;
    @JsonProperty("max_price_per_night")
    private Double maxPricePerNight;
    private String currency;
}
