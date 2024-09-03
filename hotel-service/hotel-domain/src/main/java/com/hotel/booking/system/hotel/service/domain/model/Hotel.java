package com.hotel.booking.system.hotel.service.domain.model;

import com.hotel.booking.system.common.common.enums.Stars;
import com.hotel.booking.system.common.domain.Address;
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
public class Hotel extends BaseId {

    private String name;
    private Stars stars;
    private Address address;
    private Double minPricePerNight;
    private Double maxPricePerNight;
    private String currency;
    private int checkinHour;
    private int checkoutHour;

    private Set<Room> rooms;
}
