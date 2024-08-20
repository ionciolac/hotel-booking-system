package com.hotel.booking.system.hotel.service.adapters.in.rest.adapter;

import com.hotel.booking.system.common.rest.data.res.DeletedResponse;
import com.hotel.booking.system.hotel.service.adapters.in.rest.data.req.feedback.CreateFeedbackRequest;
import com.hotel.booking.system.hotel.service.adapters.in.rest.data.req.feedback.UpdateFeedbackRequest;
import com.hotel.booking.system.hotel.service.adapters.in.rest.data.res.feedback.FeedbackResponse;
import com.hotel.booking.system.hotel.service.adapters.in.rest.mapper.FeedbackRestMapper;
import com.hotel.booking.system.hotel.service.ports.in.rest.FeedbackInPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.hotel.booking.system.common.domain.utils.AppCommonMessages.CONTROLLER_DELETE_MESSAGE_RESPONSE_MESSAGE;
import static com.hotel.booking.system.common.domain.utils.AppCommonMessages.FEEDBACK;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RequestMapping("/feedback")
@RestController
public class FeedbackRestAdapter {

    private final FeedbackInPort feedbackInPort;
    private final FeedbackRestMapper feedbackRestMapper;

    @PostMapping
    public ResponseEntity<FeedbackResponse> createFeedback(@RequestBody CreateFeedbackRequest req) {
        var feedback = feedbackInPort.createFeedback(feedbackRestMapper.toFeedback(req));
        return ResponseEntity.status(CREATED).body(feedbackRestMapper.toFeedbackResponse(feedback));
    }

    @PatchMapping
    public ResponseEntity<FeedbackResponse> updateFeedback(@RequestBody UpdateFeedbackRequest req) {
        var feedback = feedbackInPort.updateFeedback(feedbackRestMapper.toFeedback(req));
        return ResponseEntity.status(OK).body(feedbackRestMapper.toFeedbackResponse(feedback));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletedResponse> deleteFeedback(@PathVariable("id") UUID id) {
        feedbackInPort.deleteFeedback(id);
        var msg = format(CONTROLLER_DELETE_MESSAGE_RESPONSE_MESSAGE, FEEDBACK, id);
        return ResponseEntity.status(OK).body(new DeletedResponse(Boolean.TRUE, msg));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackResponse> getFeedback(@PathVariable("id") UUID id) {
        var feedback = feedbackInPort.getFeedback(id);
        return ResponseEntity.status(OK).body(feedbackRestMapper.toFeedbackResponse(feedback));
    }

    @GetMapping
    public ResponseEntity<Page<FeedbackResponse>> getHotelFeedback(@RequestParam(name = "user_id") UUID hotelId,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        var pageable = PageRequest.of(page, size);
        var result = feedbackInPort.getHotelFeedback(hotelId, pageable)
                .map(feedbackRestMapper::toFeedbackResponse);
        return ResponseEntity.status(OK).body(result);
    }
}
