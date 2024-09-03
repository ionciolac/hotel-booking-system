package com.hotel.booking.system.hotel.service.domain.service;

import com.hotel.booking.system.common.common.exception.AlreadyExistException;
import com.hotel.booking.system.common.common.exception.NotFoundException;
import com.hotel.booking.system.hotel.service.domain.model.Feedback;
import com.hotel.booking.system.hotel.service.domain.ports.in.rest.FeedbackInPort;
import com.hotel.booking.system.hotel.service.domain.ports.in.rest.HotelInPort;
import com.hotel.booking.system.hotel.service.domain.ports.out.persistence.FeedbackOutPort;
import com.hotel.booking.system.hotel.service.domain.wrapper.FeedbackWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.hotel.booking.system.common.common.utils.AppCommonMessages.*;
import static com.hotel.booking.system.common.common.utils.AppConstants.FEEDBACK_MARK_VALUES;
import static java.lang.String.format;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@Service
public class FeedbackService implements FeedbackInPort {

    private final FeedbackOutPort feedbackOutPort;
    // services
    private final HotelInPort hotelInPort;

    @Override
    public Feedback createFeedback(Feedback feedback) {
        var hotelId = feedback.getHotelId();
        var userId = feedback.getUserId();
        checkIfUserHasAddFeedbackToHotel(userId, hotelId);
        feedback.generateID();
        var hotel = hotelInPort.getHotel(hotelId);
        var feedbackWrapper = FeedbackWrapper.builder().feedback(feedback).hotel(hotel).build();
        return feedbackOutPort.upsertFeedback(feedbackWrapper);
    }

    @Override
    public Feedback updateFeedback(Feedback feedback) {
        var id = feedback.getId();
        var dbFeedback = getDBFeedback(id);
        var hotel = hotelInPort.getHotel(dbFeedback.getHotelId());
        patch(dbFeedback, feedback);
        var feedbackWrapper = FeedbackWrapper.builder().hotel(hotel).feedback(dbFeedback).build();
        return feedbackOutPort.upsertFeedback(feedbackWrapper);
    }

    @Override
    public void deleteFeedback(UUID id) {
        getDBFeedback(id);
        feedbackOutPort.deleteFeedback(id);
    }

    @Override
    public Feedback getFeedback(UUID id) {
        return getDBFeedback(id);
    }

    @Override
    public Page<Feedback> getHotelFeedback(UUID hotelId, Pageable pageable) {
        return feedbackOutPort.getHotelFeedback(hotelId, pageable);
    }

    private Feedback getDBFeedback(UUID id) {
        var feedback = feedbackOutPort.getFeedback(id);
        if (feedback.isPresent())
            return feedback.get();
        else
            throw new NotFoundException(format(SERVICE_OBJECT_WAS_NOT_FOUND_IN_DB_MESSAGE, FEEDBACK, id));
    }

    void checkIfUserHasAddFeedbackToHotel(UUID userId, UUID hotelId) {
        if (feedbackOutPort.hasUserAddFeedbackToHotel(userId, hotelId))
            throw new AlreadyExistException(format(SERVICE_USER_ALREADY_ADDED_FEEDBACK_MESSAGE, userId, hotelId));
    }

    void patch(Feedback target, Feedback source) {
        if (hasText(source.getUserMessage())) {
            target.setUserMessage(source.getUserMessage());
        }
        if (FEEDBACK_MARK_VALUES.contains(source.getUserMark())) {
            target.setUserMark(source.getUserMark());
        }
    }
}
