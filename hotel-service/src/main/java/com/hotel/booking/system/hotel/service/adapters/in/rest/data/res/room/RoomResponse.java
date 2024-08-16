package com.hotel.booking.system.hotel.service.adapters.in.rest.data.res.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel.booking.system.common.common.RoomType;
import lombok.*;

import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse {

    private UUID id;
    @JsonProperty("hotel_id")
    private UUID hotelId;
    @JsonProperty("room_type")
    private RoomType roomType;
    private int floor;
    @JsonProperty("door_number")
    private String doorNumber;
    @JsonProperty("is_room_available")
    private Boolean isRoomAvailable;
    @JsonProperty("price_per_night")
    private double pricePerNight;
    private String currency;
}
