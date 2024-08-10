package com.hotel.booking.system.hotel.service.ports.out;

import com.hotel.booking.system.hotel.service.domain.model.Hotel;

import java.util.Optional;
import java.util.UUID;

public interface HotelOutPort {

    Hotel upsertHotel(Hotel hotel);

    void deleteHotel(UUID id);

    Optional<Hotel> getHotel(UUID id);

    boolean checkIfHotelExistOnAddress(Hotel hotel);
}
