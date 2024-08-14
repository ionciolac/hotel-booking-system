package com.hotel.booking.system.hotel.service.domain.model;


import com.hotel.booking.system.common.common.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static java.util.UUID.randomUUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    private UUID id;
    private Hotel hotel;
    private RoomType roomType;
    private int floor;
    private String doorNumber;
    private Boolean isRoomAvailable;
    private double pricePerNight;
    private String currency;

    public void generateID() {
        this.id = randomUUID();
    }
}
