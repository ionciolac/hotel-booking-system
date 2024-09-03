package com.hotel.booking.system.hotel.service.application.adapter;

import com.hotel.booking.system.common.application.data.res.booking.AvailableRoomResponse;
import com.hotel.booking.system.common.application.data.res.booking.IsRoomBookedResponse;
import com.hotel.booking.system.hotel.service.application.mapper.BookingRoomRestMapper;
import com.hotel.booking.system.hotel.service.domain.ports.in.rest.BookingRoomInPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RequestMapping()
@RestController
public class BookingRoomRestAdapter {

    private final BookingRoomInPort bookingRoomInPort;
    private final BookingRoomRestMapper bookingRoomRestMapper;

    @GetMapping("/is-room-available")
    public ResponseEntity<IsRoomBookedResponse> checkIfRoomIsBooked(@RequestParam("room_id") UUID roomId,
                                                                    @RequestParam("user_id") UUID userId,
                                                                    @RequestParam("from_date") LocalDateTime fromDate,
                                                                    @RequestParam("to_date") LocalDateTime toDate) {
        var isBooked = bookingRoomInPort.checkIfRoomIsBooked(roomId, userId, fromDate, toDate);
        var result = IsRoomBookedResponse.builder().id(roomId).roomIsBooked(isBooked).build();
        return ResponseEntity.status(OK).body(result);
    }

    @GetMapping("/available-room")
    public ResponseEntity<Page<AvailableRoomResponse>> getAvailableRooms(@RequestParam(defaultValue = "0") int page,
                                                                         @RequestParam(defaultValue = "10") int size,
                                                                         @RequestParam(value = "country", required = false) String country,
                                                                         @RequestParam(value = "city", required = false) String city,
                                                                         @RequestParam(value = "from_date", required = false) LocalDateTime fromDate,
                                                                         @RequestParam(value = "to_date", required = false) LocalDateTime toDate,
                                                                         @RequestParam(value = "min_price_per_night", required = false) Double minPricePerNight,
                                                                         @RequestParam(value = "max_price_per_night", required = false) Double maxPricePerNight) {
        var result = bookingRoomInPort
                .getAvailableRooms(country, city, fromDate, toDate, minPricePerNight, maxPricePerNight, of(page, size))
                .map(bookingRoomRestMapper::toBookingResponse);
        return ResponseEntity.status(OK).body(result);
    }
}
