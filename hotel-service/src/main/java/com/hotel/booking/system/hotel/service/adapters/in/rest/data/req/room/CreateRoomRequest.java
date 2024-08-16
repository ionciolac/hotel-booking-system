package com.hotel.booking.system.hotel.service.adapters.in.rest.data.req.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel.booking.system.common.common.RoomType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
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
