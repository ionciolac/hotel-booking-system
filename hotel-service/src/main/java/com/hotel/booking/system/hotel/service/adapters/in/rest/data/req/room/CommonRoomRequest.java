package com.hotel.booking.system.hotel.service.adapters.in.rest.data.req.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonRoomRequest {
    @JsonProperty("is_room_available")
    private Boolean isRoomAvailable;
    @JsonProperty("price_per_night")
    private double pricePerNight;
}
