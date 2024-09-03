package com.hotel.booking.system.hotel.service.application.adapter;

import com.hotel.booking.system.common.application.data.res.DeletedResponse;
import com.hotel.booking.system.hotel.service.application.data.req.feedback.CreateHotelFeedbackRequest;
import com.hotel.booking.system.hotel.service.application.data.req.feedback.UpdateHotelFeedbackRequest;
import com.hotel.booking.system.hotel.service.application.data.res.feedback.HotelFeedbackResponse;
import com.hotel.booking.system.hotel.service.application.mapper.HotelFeedbackRestMapper;
import com.hotel.booking.system.hotel.service.domain.ports.in.rest.HotelFeedbackInPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.hotel.booking.system.common.common.utils.AppCommonMessages.CONTROLLER_DELETE_MESSAGE_RESPONSE_MESSAGE;
import static com.hotel.booking.system.common.common.utils.AppCommonMessages.FEEDBACK;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RequestMapping("/hotel-feedback")
@RestController
public class HotelFeedbackRestAdapter {

    private final HotelFeedbackInPort hotelFeedbackInPort;
    private final HotelFeedbackRestMapper hotelFeedbackRestMapper;

    @PostMapping
    public ResponseEntity<HotelFeedbackResponse> createHotelFeedback(@RequestBody CreateHotelFeedbackRequest req) {
        var feedback = hotelFeedbackInPort.createHotelFeedback(hotelFeedbackRestMapper.toHotelFeedback(req));
        return ResponseEntity.status(CREATED).body(hotelFeedbackRestMapper.toHotelFeedbackResponse(feedback));
    }

    @PatchMapping
    public ResponseEntity<HotelFeedbackResponse> updateHotelFeedback(@RequestBody UpdateHotelFeedbackRequest req) {
        var feedback = hotelFeedbackInPort.updateHotelFeedback(hotelFeedbackRestMapper.toHotelFeedback(req));
        return ResponseEntity.status(OK).body(hotelFeedbackRestMapper.toHotelFeedbackResponse(feedback));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletedResponse> deleteHotelFeedback(@PathVariable("id") UUID id) {
        hotelFeedbackInPort.deleteHotelFeedback(id);
        var msg = format(CONTROLLER_DELETE_MESSAGE_RESPONSE_MESSAGE, FEEDBACK, id);
        return ResponseEntity.status(OK).body(new DeletedResponse(Boolean.TRUE, msg));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelFeedbackResponse> getHotelFeedback(@PathVariable("id") UUID id) {
        var feedback = hotelFeedbackInPort.getHotelFeedback(id);
        return ResponseEntity.status(OK).body(hotelFeedbackRestMapper.toHotelFeedbackResponse(feedback));
    }

    @GetMapping
    public ResponseEntity<Page<HotelFeedbackResponse>> getHotelFeedback(@RequestParam(name = "hotel_id") UUID hotelId,
                                                                        @RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "10") int size) {
        var result = hotelFeedbackInPort.getHotelFeedback(hotelId, PageRequest.of(page, size))
                .map(hotelFeedbackRestMapper::toHotelFeedbackResponse);
        return ResponseEntity.status(OK).body(result);
    }
}
