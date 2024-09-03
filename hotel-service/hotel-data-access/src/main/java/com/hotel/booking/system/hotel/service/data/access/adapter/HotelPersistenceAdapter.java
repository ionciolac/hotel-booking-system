package com.hotel.booking.system.hotel.service.data.access.adapter;

import com.hotel.booking.system.hotel.service.data.access.mapper.HotelPersistenceMapper;
import com.hotel.booking.system.hotel.service.data.access.repository.HotelRepository;
import com.hotel.booking.system.hotel.service.domain.model.Hotel;
import com.hotel.booking.system.hotel.service.domain.ports.out.persistence.HotelOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HotelPersistenceAdapter implements HotelOutPort {

    // repository
    private final HotelRepository hotelRepository;
    // mappers
    private final HotelPersistenceMapper hotelPersistenceMapper;

    @Override
    public Hotel upsertHotel(Hotel hotel) {
        var hotelEntity = hotelPersistenceMapper.toHotelEntity(hotel);
        hotelEntity = hotelRepository.save(hotelEntity);
        return hotelPersistenceMapper.toHotel(hotelEntity);
    }

    @Override
    public void deleteHotel(UUID id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public Optional<Hotel> getHotel(UUID id) {
        return hotelRepository.findById(id).map(hotelPersistenceMapper::toHotel);
    }

    @Override
    public boolean checkIfHotelExistOnAddress(Hotel hotel) {
        var hotelAddress = hotel.getAddress();
        return hotelRepository.existsByNameAndAddressCountryAndAddressRegionAndAddressCityAndAddressPostalCodeAndAddressStreetAndAddressBuildingNumber(
                hotel.getName(),
                hotelAddress.getCountry(),
                hotelAddress.getRegion(),
                hotelAddress.getCity(),
                hotelAddress.getPostalCode(),
                hotelAddress.getStreet(),
                hotelAddress.getBuildingNumber()
        );
    }
}
