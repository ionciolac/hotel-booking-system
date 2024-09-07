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
import static java.lang.String.format;

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
        var customerId = hotelFeedback.getCustomerId();
        checkIfCustomerHasAddFeedbackToHotel(customerId, hotelId);
        var hotel = hotelInPort.getHotel(hotelId);
        hotelFeedback.setHotel(hotel);
        hotelFeedback.generateID();
        return hotelFeedbackOutPort.upsertHotelFeedback(hotelFeedback);
    }

    @Transactional
    @Override
    public HotelFeedback updateHotelFeedback(HotelFeedback hotelFeedback) {
        var id = hotelFeedback.getId();
        var dbHotelFeedback = getDBFeedback(id);
        dbHotelFeedback.patch(dbHotelFeedback, hotelFeedback);
        return hotelFeedbackOutPort.upsertHotelFeedback(dbHotelFeedback);
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
        var hotelFeedback = hotelFeedbackOutPort.getHotelFeedback(id);
        if (hotelFeedback.isPresent())
            return hotelFeedback.get();
        else
            throw new NotFoundException(format(SERVICE_OBJECT_WAS_NOT_FOUND_IN_DB_MESSAGE, FEEDBACK, id));
    }

    void checkIfCustomerHasAddFeedbackToHotel(UUID customerId, UUID hotelId) {
        if (hotelFeedbackOutPort.hasCustomerAddFeedbackToHotel(customerId, hotelId))
            throw new AlreadyExistException(format(SERVICE_CUSTOMER_ALREADY_ADDED_FEEDBACK_MESSAGE, customerId, HOTEL, hotelId));
    }
}
