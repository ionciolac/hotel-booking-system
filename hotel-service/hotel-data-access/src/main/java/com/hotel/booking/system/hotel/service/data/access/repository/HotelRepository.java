package com.hotel.booking.system.hotel.service.data.access.repository;

import com.hotel.booking.system.hotel.service.data.access.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HotelRepository extends JpaRepository<HotelEntity, UUID> {

    boolean existsByNameAndAddressCountryAndAddressRegionAndAddressCityAndAddressPostalCodeAndAddressStreetAndAddressBuildingNumber(
            String name, String country, String region, String city, String postalCode, String street, String buildingNumber
    );
}
