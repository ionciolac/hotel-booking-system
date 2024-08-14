package com.hotel.booking.system.hotel.service.adapters.in.rest.data.req.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoomRequest extends CommonRoomRequest {

    private UUID id;
    @JsonProperty("is_room_available")
    private Boolean isRoomAvailable;
    @JsonProperty("price_per_night")
    private double pricePerNight;
}
