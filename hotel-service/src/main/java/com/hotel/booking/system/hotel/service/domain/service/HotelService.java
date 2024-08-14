package com.hotel.booking.system.hotel.service.domain.service;

import com.hotel.booking.system.common.domain.exception.AlreadyExistException;
import com.hotel.booking.system.common.domain.exception.NotFoundException;
import com.hotel.booking.system.hotel.service.domain.model.Hotel;
import com.hotel.booking.system.hotel.service.ports.in.HotelInPort;
import com.hotel.booking.system.hotel.service.ports.out.HotelOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    @Override
    public Hotel updateHotel(Hotel hotel) {
        var id = hotel.getId();
        var dbHotel = getDBHotel(id);
        var hotelAddressId = dbHotel.getAddress().getId();
        var hotelAddress = hotel.getAddress();
        hotelAddress.setId(hotelAddressId);
        hotel.setAddress(hotelAddress);
        return hotelOutPort.upsertHotel(hotel);
    }

    @Override
    public void deleteHotel(UUID id) {
        getDBHotel(id);
        hotelOutPort.deleteHotel(id);
    }

    @Override
    public Hotel getHotel(UUID id) {
        return getDBHotel(id);
    }

    private void checkIfHotelExistOnAddress(Hotel hotel) {
        if (hotelOutPort.checkIfHotelExistOnAddress(hotel)) {
            String msg = format("Hotel with name: %s with entered address already exist in DB.", hotel.getName());
            throw new AlreadyExistException(msg);
        }
    }

    private Hotel getDBHotel(UUID id) {
        var hotel = hotelOutPort.getHotel(id);
        if (hotel.isPresent())
            return hotel.get();
        else {
            var msg = format("Hotel was not found in DB by id: %s.", id);
            throw new NotFoundException(msg);
        }
    }
}
