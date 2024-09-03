package com.hotel.booking.system.hotel.service.application.data.res.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel.booking.system.common.application.data.res.address.AddressResponse;
import com.hotel.booking.system.common.common.enums.Stars;
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
    @JsonProperty("checkin_hour")
    private int checkinHour;
    @JsonProperty("checkout_hour")
    private int checkoutHour;
}
