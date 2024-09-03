package com.hotel.booking.system.booking.service.domain.client;

import com.hotel.booking.system.common.application.data.res.booking.IsRoomBookedResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.UUID;

@FeignClient(url = "${booking-service.hotel-service-address}:${booking-service.hotel-service-port}", name = "hotel-service")
public interface HotelServiceClient {

    @GetMapping("/is-room-available")
    ResponseEntity<IsRoomBookedResponse> checkIfRoomIsBooked(@RequestParam("room_id") UUID roomId,
                                                             @RequestParam("user_id") UUID userId,
                                                             @RequestParam("from_date") LocalDateTime fromDate,
                                                             @RequestParam("to_date") LocalDateTime toDate);
}
