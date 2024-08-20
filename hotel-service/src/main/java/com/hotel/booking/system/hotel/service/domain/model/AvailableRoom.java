package com.hotel.booking.system.hotel.service.domain.model;

import lombok.*;

import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableRoom {

    private UUID hotelId;
    private String hotelName;
    private UUID roomId;
    private double pricePerNight;
    private String currency;
    private String country;
    private String city;
}
