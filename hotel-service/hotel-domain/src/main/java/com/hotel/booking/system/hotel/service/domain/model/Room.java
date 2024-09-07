package com.hotel.booking.system.hotel.service.domain.model;

import com.hotel.booking.system.common.common.enums.RoomType;
import com.hotel.booking.system.common.domain.BaseId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Room extends BaseId {

    private Hotel hotel;
    private RoomType roomType;
    private int floor;
    private String doorNumber;
    private Boolean isRoomAvailable;
    private double pricePerNight;
    private String currency;

    private Set<RoomBooking> roomBookings;

    public void patch(Room target, Room source) {
        if (source.getIsRoomAvailable() != null) {
            target.setIsRoomAvailable(source.getIsRoomAvailable());
        }
        if (source.getPricePerNight() != 0) {
            target.setPricePerNight(source.getPricePerNight());
        }
    }
}
