package com.hotel.booking.system.hotel.service.data.access.adapter;

import com.hotel.booking.system.hotel.service.data.access.entity.FeedbackEntity;
import com.hotel.booking.system.hotel.service.data.access.entity.HotelEntity;
import com.hotel.booking.system.hotel.service.data.access.mapper.FeedbackPersistenceMapper;
import com.hotel.booking.system.hotel.service.data.access.mapper.HotelPersistenceMapper;
import com.hotel.booking.system.hotel.service.data.access.repository.FeedbackRepository;
import com.hotel.booking.system.hotel.service.domain.model.Feedback;
import com.hotel.booking.system.hotel.service.domain.ports.out.persistence.FeedbackOutPort;
import com.hotel.booking.system.hotel.service.domain.wrapper.FeedbackWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class FeedbackPersistenceAdapter implements FeedbackOutPort {

    // repository
    private final FeedbackRepository feedbackRepository;
    // mappers
    private final FeedbackPersistenceMapper feedbackPersistenceMapper;
    private final HotelPersistenceMapper hotelPersistenceMapper;

    @Override
    public Feedback upsertFeedback(FeedbackWrapper feedbackWrapper) {
        FeedbackEntity feedbackEntity = feedbackPersistenceMapper.toFeedbackEntity(feedbackWrapper.getFeedback());
        HotelEntity hotelEntity = hotelPersistenceMapper.toHotelEntity(feedbackWrapper.getHotel());
        feedbackEntity.setHotel(hotelEntity);
        feedbackEntity = feedbackRepository.save(feedbackEntity);
        return feedbackPersistenceMapper.toFeedback(feedbackEntity);
    }

    @Override
    public void deleteFeedback(UUID id) {
        feedbackRepository.deleteById(id);
    }

    @Override
    public Optional<Feedback> getFeedback(UUID id) {
        return feedbackRepository.findById(id).map(feedbackPersistenceMapper::toFeedback);
    }

    @Override
    public boolean hasUserAddFeedbackToHotel(UUID userId, UUID hotelId) {
        return feedbackRepository.existsByUserIdAndHotelId(userId, hotelId);
    }

    @Override
    public Page<Feedback> getHotelFeedback(UUID hotelId, Pageable pageable) {
        return feedbackRepository.findAllByHotelId(hotelId, pageable).map(feedbackPersistenceMapper::toFeedback);
    }
}
