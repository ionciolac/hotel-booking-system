package com.hotel.booking.system.hotel.service.adapters.in.rest.data.res.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel.booking.system.common.common.Stars;
import com.hotel.booking.system.common.rest.data.res.address.AddressResponse;
import lombok.*;

import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponse {

    private UUID id;
    private String name;
    private Stars stars;
    private AddressResponse address;
    @JsonProperty("min_price_per_night")
    private Double minPricePerNight;
    @JsonProperty("max_price_per_night")
    private Double maxPricePerNight;
    private String currency;
}
