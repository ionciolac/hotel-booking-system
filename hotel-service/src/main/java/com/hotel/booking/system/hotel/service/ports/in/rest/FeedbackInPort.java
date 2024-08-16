package com.hotel.booking.system.hotel.service.ports.in.rest;

import com.hotel.booking.system.hotel.service.domain.model.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FeedbackInPort {

    Feedback createFeedback(Feedback feedback);

    Feedback updateFeedback(Feedback feedback);

    void deleteFeedback(UUID id);

    Feedback getFeedback(UUID id);

    Page<Feedback> getHotelFeedback(UUID hotelId, Pageable pageable);
}
