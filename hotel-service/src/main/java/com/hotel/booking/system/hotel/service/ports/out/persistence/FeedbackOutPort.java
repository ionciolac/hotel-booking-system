package com.hotel.booking.system.hotel.service.ports.out.persistence;

import com.hotel.booking.system.hotel.service.domain.model.Feedback;
import com.hotel.booking.system.hotel.service.domain.wrapper.FeedbackWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface FeedbackOutPort {

    Feedback upsertFeedback(FeedbackWrapper feedbackWrapper);

    void deleteFeedback(UUID id);

    Optional<Feedback> getFeedback(UUID id);

    boolean hasUserAddFeedbackToHotel(UUID userId, UUID hotelId);

    Page<Feedback> getHotelFeedback(UUID hotelId, Pageable pageable);
}
