package com.hotel.booking.system.common.rest.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    private Instant date;
    private String message;
}
