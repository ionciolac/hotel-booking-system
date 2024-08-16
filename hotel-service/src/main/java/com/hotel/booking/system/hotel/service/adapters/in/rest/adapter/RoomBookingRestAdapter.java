package com.hotel.booking.system.hotel.service.adapters.in.rest.adapter;

import com.hotel.booking.system.hotel.service.adapters.in.rest.data.res.roombooking.IsRoomBookedResponse;
import com.hotel.booking.system.hotel.service.ports.in.rest.RoomBookingInPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/room-booking")
@RestController
public class RoomBookingRestAdapter {

    private final RoomBookingInPort roomBookingInPort;

    @GetMapping
    public ResponseEntity<IsRoomBookedResponse> checkIfRoomIsBooked(@RequestParam UUID id,
                                                                    @RequestParam LocalDateTime fromDate,
                                                                    @RequestParam LocalDateTime toDate) {
        var isBooked = roomBookingInPort.checkIfRoomIsBooked(id, fromDate, toDate);
        var result = IsRoomBookedResponse.builder().id(id).roomIsBooked(isBooked).build();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
