package com.hotel.booking.system.common.application.data.res.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableRoomResponse {

    @JsonProperty("hotel_id")
    private UUID hotelId;
    @JsonProperty("hotel_name")
    private String hotelName;
    @JsonProperty("room_id")
    private UUID roomId;
    @JsonProperty("price_per_night")
    private double pricePerNight;
    private String currency;
    private String country;
    private String city;
}
