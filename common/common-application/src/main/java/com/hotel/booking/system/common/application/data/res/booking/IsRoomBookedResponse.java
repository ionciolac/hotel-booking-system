package com.hotel.booking.system.common.application.data.res.booking;

import lombok.*;

import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IsRoomBookedResponse {

    private UUID id;
    private boolean roomIsBooked;
}
