package com.hotel.booking.system.hotel.service.domain.ports.out.persistence;

import com.hotel.booking.system.hotel.service.domain.model.HotelFeedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface HotelFeedbackOutPort {

    HotelFeedback upsertHotelFeedback(HotelFeedback hotelFeedback);

    void deleteHotelFeedback(UUID id);

    Optional<HotelFeedback> getHotelFeedback(UUID id);

    boolean hasUserAddFeedbackToHotel(UUID userId, UUID hotelId);

    Page<HotelFeedback> getHotelFeedback(UUID hotelId, Pageable pageable);
}
