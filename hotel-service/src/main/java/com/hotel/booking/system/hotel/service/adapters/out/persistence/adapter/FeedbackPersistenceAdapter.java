package com.hotel.booking.system.hotel.service.adapters.out.persistence.adapter;

import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.FeedbackEntity;
import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.HotelEntity;
import com.hotel.booking.system.hotel.service.adapters.out.persistence.mapper.FeedbackPersistenceMapper;
import com.hotel.booking.system.hotel.service.adapters.out.persistence.mapper.HotelPersistenceMapper;
import com.hotel.booking.system.hotel.service.adapters.out.persistence.repository.FeedbackRepository;
import com.hotel.booking.system.hotel.service.domain.model.Feedback;
import com.hotel.booking.system.hotel.service.domain.wrapper.FeedbackWrapper;
import com.hotel.booking.system.hotel.service.ports.out.persistence.FeedbackOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FeedbackPersistenceAdapter implements FeedbackOutPort {

    private final FeedbackRepository feedbackRepository;
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
