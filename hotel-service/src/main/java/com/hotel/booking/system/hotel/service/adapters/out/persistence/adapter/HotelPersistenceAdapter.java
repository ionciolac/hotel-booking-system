package com.hotel.booking.system.hotel.service.adapters.out.persistence.adapter;

import com.hotel.booking.system.hotel.service.adapters.out.persistence.mapper.HotelPersistenceMapper;
import com.hotel.booking.system.hotel.service.adapters.out.persistence.repository.HotelRepository;
import com.hotel.booking.system.hotel.service.domain.model.Hotel;
import com.hotel.booking.system.hotel.service.ports.out.persistence.HotelOutPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class HotelPersistenceAdapter implements HotelOutPort {

    private final HotelRepository hotelRepository;
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
