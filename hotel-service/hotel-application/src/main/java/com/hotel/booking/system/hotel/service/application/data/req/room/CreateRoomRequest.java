package com.hotel.booking.system.hotel.service.application.data.req.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel.booking.system.common.common.enums.RoomType;
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
public class CreateRoomRequest extends CommonRoomRequest {

    @JsonProperty("hotel_id")
    private UUID hotelId;
    @JsonProperty("room_type")
    private RoomType roomType;
    private int floor;
    @JsonProperty("door_number")
    private String doorNumber;
}
