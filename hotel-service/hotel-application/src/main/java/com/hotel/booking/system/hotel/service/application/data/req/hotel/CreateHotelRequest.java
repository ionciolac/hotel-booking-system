package com.hotel.booking.system.hotel.service.application.data.req.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel.booking.system.common.application.data.req.address.AddressRequest;
import com.hotel.booking.system.common.common.enums.Stars;
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
public class CreateHotelRequest {

    private String name;
    private Stars stars;
    private AddressRequest address;
    @JsonProperty("min_price_per_night")
    private Double minPricePerNight;
    @JsonProperty("max_price_per_night")
    private Double maxPricePerNight;
    private String currency;
    @JsonProperty("checkin_hour")
    private int checkinHour;
    @JsonProperty("checkout_hour")
    private int checkoutHour;
}
