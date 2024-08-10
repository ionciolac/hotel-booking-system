package com.hotel.booking.system.hotel.service.adapters.out.persistence.repository;

import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HotelRepository extends JpaRepository<HotelEntity, UUID> {

    boolean existsByNameAndAddressCountryAndAddressRegionAndAddressCityAndAddressPostalCodeAndAddressStreetAndAddressBuildingNumber(
            String name, String country, String region, String city, String postalCode, String street, String buildingNumber
    );
}
