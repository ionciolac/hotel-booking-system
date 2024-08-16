package com.hotel.booking.system.hotel.service.adapters.in.rest.data.res.roombooking;

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
