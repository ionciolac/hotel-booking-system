package com.hotel.booking.system.hotel.service.ports.in.rest;

import com.hotel.booking.system.hotel.service.domain.model.Hotel;

import java.util.UUID;

public interface HotelInPort {

    Hotel createHotel(Hotel hotel);

    Hotel updateHotel(Hotel hotel);

    void deleteHotel(UUID id);

    Hotel getHotel(UUID id);
}
