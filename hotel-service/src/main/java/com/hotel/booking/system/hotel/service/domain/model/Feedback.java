package com.hotel.booking.system.hotel.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {

    private UUID id;
    private UUID hotelId;
    private UUID userId;
    private String userFullName;
    private String userMessage;
    private double userMark;
}
