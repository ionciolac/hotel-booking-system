package com.hotel.booking.system.hotel.service.domain.service;

import com.hotel.booking.system.common.common.exception.AlreadyExistException;
import com.hotel.booking.system.common.common.exception.NotFoundException;
import com.hotel.booking.system.hotel.service.domain.model.Hotel;
import com.hotel.booking.system.hotel.service.domain.ports.in.rest.HotelInPort;
import com.hotel.booking.system.hotel.service.domain.ports.out.persistence.HotelOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.hotel.booking.system.common.common.utils.AppCommonMessages.*;
import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class HotelService implements HotelInPort {

    private final HotelOutPort hotelOutPort;

    @Override
    public Hotel createHotel(Hotel hotel) {
        checkIfHotelExistOnAddress(hotel);
        var hotelAddress = hotel.getAddress();
        hotelAddress.generateID();
        hotel.generateID();
        hotel.setAddress(hotelAddress);
        return hotelOutPort.upsertHotel(hotel);
    }

    @Transactional
    @Override
    public Hotel updateHotel(Hotel hotel) {
        var id = hotel.getId();
        var dbHotel = getDBHotel(id);
        dbHotel.patch(dbHotel, hotel);
        return hotelOutPort.upsertHotel(dbHotel);
    }

    @Transactional
    @Override
    public void deleteHotel(UUID id) {
        getDBHotel(id);
        hotelOutPort.deleteHotel(id);
    }

    @Transactional
    @Override
    public Hotel getHotel(UUID id) {
        return getDBHotel(id);
    }

    private void checkIfHotelExistOnAddress(Hotel hotel) {
        if (hotelOutPort.checkIfHotelExistOnAddress(hotel))
            throw new AlreadyExistException(format(SERVICE_HOTEL_ALREADY_EXIST_ON_ADDRESS_MESSAGE, hotel.getName()));
    }

    private Hotel getDBHotel(UUID id) {
        var hotel = hotelOutPort.getHotel(id);
        if (hotel.isPresent())
            return hotel.get();
        else
            throw new NotFoundException(format(SERVICE_OBJECT_WAS_NOT_FOUND_IN_DB_MESSAGE, HOTEL, id));
    }
}
