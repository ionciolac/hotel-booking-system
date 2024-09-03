package com.hotel.booking.system.hotel.service.domain.model;

import com.hotel.booking.system.common.domain.BaseId;
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
public class HotelFeedback extends BaseId {

    private UUID userId;
    private Hotel hotel;
    private String userFullName;
    private String userMessage;
    private double userMark;
}
