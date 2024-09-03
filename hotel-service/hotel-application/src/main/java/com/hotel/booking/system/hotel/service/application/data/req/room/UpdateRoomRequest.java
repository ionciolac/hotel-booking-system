package com.hotel.booking.system.hotel.service.application.data.req.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoomRequest extends CommonRoomRequest {

    private UUID id;
    @JsonProperty("is_room_available")
    private Boolean isRoomAvailable;
    @JsonProperty("price_per_night")
    private double pricePerNight;
}
