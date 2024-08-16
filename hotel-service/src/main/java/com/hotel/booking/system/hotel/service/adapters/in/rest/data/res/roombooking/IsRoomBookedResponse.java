package com.hotel.booking.system.hotel.service.adapters.in.rest.data.res.roombooking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IsRoomBookedResponse {

    private UUID id;
    private boolean roomIsBooked;
}
