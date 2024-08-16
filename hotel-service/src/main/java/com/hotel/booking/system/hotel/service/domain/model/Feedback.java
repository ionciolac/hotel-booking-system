package com.hotel.booking.system.hotel.service.domain.model;

import lombok.*;

import java.util.UUID;

import static java.util.UUID.randomUUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {

    private UUID id;
    private UUID hotelId;
    private UUID userId;
    private String userFullName;
    private String userMessage;
    private double userMark;

    public void generateID() {
        this.id = randomUUID();
    }
}
