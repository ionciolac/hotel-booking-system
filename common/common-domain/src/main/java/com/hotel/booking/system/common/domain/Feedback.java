package com.hotel.booking.system.common.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Feedback extends BaseId {

    private UUID userId;
    private String userFullName;
    private String userMessage;
    private double userMark;
}
