package com.hotel.booking.system.booking.service.application.data.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel.booking.system.common.common.enums.BookingStatus;
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
public class BookingResponse {

    private UUID id;
    @JsonProperty("customer_id")
    private UUID customerId;
    @JsonProperty("hotel_id")
    private UUID hotelId;
    @JsonProperty("room_id")
    private UUID roomId;
    @JsonProperty("from_date")
    private LocalDateTime fromDate;
    @JsonProperty("to_date")
    private LocalDateTime toDate;
    private BookingStatus status;
}
