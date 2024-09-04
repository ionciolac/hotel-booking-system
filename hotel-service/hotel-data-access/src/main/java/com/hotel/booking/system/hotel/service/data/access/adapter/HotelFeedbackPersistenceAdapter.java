package com.hotel.booking.system.hotel.service.data.access.adapter;

import com.hotel.booking.system.hotel.service.data.access.mapper.HotelFeedbackPersistenceMapper;
import com.hotel.booking.system.hotel.service.data.access.repository.HotelFeedbackRepository;
import com.hotel.booking.system.hotel.service.domain.model.HotelFeedback;
import com.hotel.booking.system.hotel.service.domain.ports.out.persistence.HotelFeedbackOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HotelFeedbackPersistenceAdapter implements HotelFeedbackOutPort {

    // repository
    private final HotelFeedbackRepository hotelFeedbackRepository;
    // mappers
    private final HotelFeedbackPersistenceMapper hotelFeedbackPersistenceMapper;

    @Override
    public HotelFeedback upsertHotelFeedback(HotelFeedback hotelFeedback) {
        var hotelFeedbackEntity = hotelFeedbackPersistenceMapper.toFeedbackEntity(hotelFeedback);
        hotelFeedbackEntity = hotelFeedbackRepository.save(hotelFeedbackEntity);
        return hotelFeedbackPersistenceMapper.toHotelFeedback(hotelFeedbackEntity);
    }

    @Override
    public void deleteHotelFeedback(UUID id) {
        hotelFeedbackRepository.deleteById(id);
    }

    @Override
    public Optional<HotelFeedback> getHotelFeedback(UUID id) {
        return hotelFeedbackRepository.findById(id).map(hotelFeedbackPersistenceMapper::toHotelFeedback);
    }

    @Override
    public boolean hasUserAddFeedbackToHotel(UUID userId, UUID hotelId) {
        return hotelFeedbackRepository.existsByUserIdAndHotelId(userId, hotelId);
    }

    @Override
    public Page<HotelFeedback> getHotelFeedback(UUID hotelId, Pageable pageable) {
        return hotelFeedbackRepository.findAllByHotelId(hotelId, pageable)
                .map(hotelFeedbackPersistenceMapper::toHotelFeedback);
    }
}
