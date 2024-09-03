package com.hotel.booking.system.hotel.service.domain.model;

import com.hotel.booking.system.common.domain.BaseId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomBooking extends BaseId {

    private Room room;
    private UUID userId;
    private UUID bookingId;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private Long nightsNumber;
    private Double pricePerNight;
    private Double totalPrice;
    private String currency;
}
