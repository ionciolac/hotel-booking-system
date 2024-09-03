package com.hotel.booking.system.hotel.service.application.data.req.room;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CommonRoomRequest {
    @JsonProperty("is_room_available")
    private Boolean isRoomAvailable;
    @JsonProperty("price_per_night")
    private double pricePerNight;
}
