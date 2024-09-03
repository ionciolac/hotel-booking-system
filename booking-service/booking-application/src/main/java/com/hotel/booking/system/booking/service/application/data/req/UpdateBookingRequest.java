package com.hotel.booking.system.booking.service.application.data.req;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UpdateBookingRequest {

    private UUID id;
    @JsonProperty("from_date")
    private LocalDateTime fromDate;
    @JsonProperty("to_date")
    private LocalDateTime toDate;
}
