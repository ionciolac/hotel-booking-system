package com.hotel.booking.system.bookingservice.adapters.in.rest.data.req;


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
public class CreateBookingRequest {

    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("hotel_id")
    private UUID hotelId;
    @JsonProperty("room_id")
    private UUID roomId;
    @JsonProperty("from_date")
    private LocalDateTime fromDate;
    @JsonProperty("to_date")
    private LocalDateTime toDate;
}
