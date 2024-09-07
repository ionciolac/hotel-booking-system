package com.hotel.booking.system.hotel.service.application.adapter;

import com.hotel.booking.system.common.application.data.res.DeletedResponse;
import com.hotel.booking.system.hotel.service.application.data.req.hotel.CreateHotelRequest;
import com.hotel.booking.system.hotel.service.application.data.req.hotel.UpdateHotelRequest;
import com.hotel.booking.system.hotel.service.application.data.res.hotel.HotelResponse;
import com.hotel.booking.system.hotel.service.application.mapper.HotelRestMapper;
import com.hotel.booking.system.hotel.service.domain.ports.in.rest.HotelInPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.hotel.booking.system.common.common.utils.AppCommonMessages.CONTROLLER_DELETE_MESSAGE_RESPONSE_MESSAGE;
import static com.hotel.booking.system.common.common.utils.AppCommonMessages.HOTEL;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RequestMapping("/hotel")
@RestController
public class HotelRestAdapter {

    private final HotelInPort hotelInPort;
    private final HotelRestMapper hotelRestMapper;

    @PostMapping
    public ResponseEntity<HotelResponse> createHotel(@RequestBody CreateHotelRequest req) {
        var hotel = hotelInPort.createHotel(hotelRestMapper.toHotel(req));
        return ResponseEntity.status(CREATED).body(hotelRestMapper.toHotelResponse(hotel));
    }

    @PatchMapping
    public ResponseEntity<HotelResponse> updateHotel(@RequestBody UpdateHotelRequest req) {
        var hotel = hotelInPort.updateHotel(hotelRestMapper.toHotel(req));
        return ResponseEntity.status(OK).body(hotelRestMapper.toHotelResponse(hotel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletedResponse> deleteHotel(@PathVariable("id") UUID id) {
        hotelInPort.deleteHotel(id);
        var msg = format(CONTROLLER_DELETE_MESSAGE_RESPONSE_MESSAGE, HOTEL, id);
        return ResponseEntity.status(OK).body(new DeletedResponse(Boolean.TRUE, msg));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponse> getHotel(@PathVariable("id") UUID id) {
        return ResponseEntity.status(OK).body(hotelRestMapper.toHotelResponse(hotelInPort.getHotel(id)));
    }
}
