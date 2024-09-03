package com.hotel.booking.system.hotel.service.domain.ports.in.rest;

import com.hotel.booking.system.hotel.service.domain.model.HotelFeedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface HotelFeedbackInPort {

    HotelFeedback createHotelFeedback(HotelFeedback hotelFeedback);

    HotelFeedback updateHotelFeedback(HotelFeedback hotelFeedback);

    void deleteHotelFeedback(UUID id);

    HotelFeedback getHotelFeedback(UUID id);

    Page<HotelFeedback> getHotelFeedback(UUID hotelId, Pageable pageable);
}
