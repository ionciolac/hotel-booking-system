package com.hotel.booking.system.hotel.service.adapters.in.rest.data.req.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel.booking.system.common.common.Stars;
import com.hotel.booking.system.common.rest.data.req.address.AddressRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHotelRequest {

    private String name;
    private Stars stars;
    private AddressRequest address;
    @JsonProperty("min_price_per_night")
    private Double minPricePerNight;
    @JsonProperty("max_price_per_night")
    private Double maxPricePerNight;
    private String currency;
}
