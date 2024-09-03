package com.hotel.booking.system.common.application.data.res;

import lombok.*;

import java.time.Instant;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    private Instant date;
    private String message;
}
