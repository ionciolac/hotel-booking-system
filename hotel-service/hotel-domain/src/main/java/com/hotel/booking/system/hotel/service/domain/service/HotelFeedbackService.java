package com.hotel.booking.system.hotel.service.domain.service;

import com.hotel.booking.system.common.common.exception.AlreadyExistException;
import com.hotel.booking.system.common.common.exception.NotFoundException;
import com.hotel.booking.system.hotel.service.domain.model.HotelFeedback;
import com.hotel.booking.system.hotel.service.domain.ports.in.rest.HotelFeedbackInPort;
import com.hotel.booking.system.hotel.service.domain.ports.in.rest.HotelInPort;
import com.hotel.booking.system.hotel.service.domain.ports.out.persistence.HotelFeedbackOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.hotel.booking.system.common.common.utils.AppCommonMessages.*;
import static com.hotel.booking.system.common.common.utils.AppConstants.FEEDBACK_MARK_VALUES;
import static java.lang.String.format;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@Service
public class HotelFeedbackService implements HotelFeedbackInPort {

    private final HotelFeedbackOutPort hotelFeedbackOutPort;
    // services
    private final HotelInPort hotelInPort;

    @Transactional
    @Override
    public HotelFeedback createHotelFeedback(HotelFeedback hotelFeedback) {
        var hotelId = hotelFeedback.getHotel().getId();
        var userId = hotelFeedback.getUserId();
        checkIfUserHasAddFeedbackToHotel(userId, hotelId);
        var hotel = hotelInPort.getHotel(hotelId);
        hotelFeedback.setHotel(hotel);
        hotelFeedback.generateID();
        return hotelFeedbackOutPort.upsertHotelFeedback(hotelFeedback);
    }

    @Transactional
    @Override
    public HotelFeedback updateHotelFeedback(HotelFeedback hotelFeedback) {
        var id = hotelFeedback.getId();
        var dbFeedback = getDBFeedback(id);
        patch(dbFeedback, hotelFeedback);
        return hotelFeedbackOutPort.upsertHotelFeedback(dbFeedback);
    }

    @Transactional
    @Override
    public void deleteHotelFeedback(UUID id) {
        getDBFeedback(id);
        hotelFeedbackOutPort.deleteHotelFeedback(id);
    }

    @Override
    public HotelFeedback getHotelFeedback(UUID id) {
        return getDBFeedback(id);
    }

    @Override
    public Page<HotelFeedback> getHotelFeedback(UUID hotelId, Pageable pageable) {
        return hotelFeedbackOutPort.getHotelFeedback(hotelId, pageable);
    }

    private HotelFeedback getDBFeedback(UUID id) {
        var feedback = hotelFeedbackOutPort.getHotelFeedback(id);
        if (feedback.isPresent())
            return feedback.get();
        else
            throw new NotFoundException(format(SERVICE_OBJECT_WAS_NOT_FOUND_IN_DB_MESSAGE, FEEDBACK, id));
    }

    void checkIfUserHasAddFeedbackToHotel(UUID userId, UUID hotelId) {
        if (hotelFeedbackOutPort.hasUserAddFeedbackToHotel(userId, hotelId))
            throw new AlreadyExistException(format(SERVICE_USER_ALREADY_ADDED_FEEDBACK_MESSAGE, userId, HOTEL, hotelId));
    }

    void patch(HotelFeedback target, HotelFeedback source) {
        if (hasText(source.getUserMessage())) {
            target.setUserMessage(source.getUserMessage());
        }
        if (FEEDBACK_MARK_VALUES.contains(source.getUserMark())) {
            target.setUserMark(source.getUserMark());
        }
    }
}
