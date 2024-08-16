package com.hotel.booking.system.hotel.service.domain.service;

import com.hotel.booking.system.common.common.FeedbackMark;
import com.hotel.booking.system.common.domain.exception.AlreadyExistException;
import com.hotel.booking.system.common.domain.exception.NotFoundException;
import com.hotel.booking.system.hotel.service.domain.model.Feedback;
import com.hotel.booking.system.hotel.service.domain.model.Hotel;
import com.hotel.booking.system.hotel.service.domain.wrapper.FeedbackWrapper;
import com.hotel.booking.system.hotel.service.ports.in.rest.FeedbackInPort;
import com.hotel.booking.system.hotel.service.ports.out.FeedbackOutPort;
import com.hotel.booking.system.hotel.service.ports.out.HotelOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@Service
public class FeedbackService implements FeedbackInPort {

    private static final List<Double> feedbackMarkValues = List.of(FeedbackMark.ONE.mark, FeedbackMark.TWO.mark,
            FeedbackMark.THREE.mark, FeedbackMark.FOUR.mark, FeedbackMark.FIVE.mark);

    private final FeedbackOutPort feedbackOutPort;
    private final HotelOutPort hotelOutPort;

    @Override
    public Feedback createFeedback(Feedback feedback) {
        var hotelId = feedback.getHotelId();
        var userId = feedback.getUserId();
        checkIfUserHasAddFeedbackToHotel(userId, hotelId);
        feedback.generateID();
        var hotel = getDBHotel(hotelId);
        var feedbackWrapper = FeedbackWrapper.builder().feedback(feedback).hotel(hotel).build();
        return feedbackOutPort.upsertFeedback(feedbackWrapper);
    }

    @Override
    public Feedback updateFeedback(Feedback feedback) {
        var id = feedback.getId();
        var dbFeedback = getDBFeedback(id);
        var hotel = getDBHotel(dbFeedback.getHotelId());
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

    private Hotel getDBHotel(UUID id) {
        Optional<Hotel> hotel = hotelOutPort.getHotel(id);
        if (hotel.isPresent()) {
            return hotel.get();
        } else {
            String msg = format("Hotel with id: %s was not found in DB. Feedback can't be added.", id);
            throw new NotFoundException(msg);
        }
    }

    private Feedback getDBFeedback(UUID id) {
        var feedback = feedbackOutPort.getFeedback(id);
        if (feedback.isPresent()) {
            return feedback.get();
        } else {
            String msg = format("Feedback with id: %s was not found in DB.", id);
            throw new NotFoundException(msg);
        }
    }

    void checkIfUserHasAddFeedbackToHotel(UUID userId, UUID hotelId) {
        if (feedbackOutPort.hasUserAddFeedbackToHotel(userId, hotelId)) {
            String msg = format("User with id: %s already added feedback to hotel with id %s", userId, hotelId);
            throw new AlreadyExistException(msg);
        }
    }

    void patch(Feedback target, Feedback source) {
        if (hasText(source.getUserMessage())) {
            target.setUserMessage(source.getUserMessage());
        }
        if (feedbackMarkValues.contains(source.getUserMark())) {
            target.setUserMark(source.getUserMark());
        }
    }
}
