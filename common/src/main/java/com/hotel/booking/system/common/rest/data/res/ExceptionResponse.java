package com.hotel.booking.system.common.rest.data.res;

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
