package com.hotel.booking.system.hotel.service.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomBooking {

    private UUID id;
    private Room room;
    private UUID userId;
    private UUID bookingId;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private Long nightsNumber;
    private Double pricePerNight;
    private Double totalPrice;
    private String currency;

    public void generateID() {
        this.id = randomUUID();
    }
}
