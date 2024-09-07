package com.hotel.booking.system.hotel.service.domain.model;

import com.hotel.booking.system.common.common.enums.Stars;
import com.hotel.booking.system.common.domain.Address;
import com.hotel.booking.system.common.domain.BaseId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

import static org.springframework.util.StringUtils.hasText;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel extends BaseId {

    private String name;
    private Stars stars;
    private Address address;
    private Double minPricePerNight;
    private Double maxPricePerNight;
    private String currency;
    private int checkinHour;
    private int checkoutHour;

    private Set<Room> rooms;

    public void patch(Hotel target, Hotel source) {
        if (hasText(source.getName())) {
            target.setName(source.getName());
        }
        if (source.getStars() != null) {
            target.setStars(source.getStars());
        }
        if (source.getAddress() != null) {
            patch(target.getAddress(), source.getAddress());
        }
        if (source.getMinPricePerNight() != null) {
            target.setMinPricePerNight(source.getMinPricePerNight());
        }
        if (source.getMaxPricePerNight() != null) {
            target.setMaxPricePerNight(source.getMaxPricePerNight());
        }
        if (hasText(source.getCurrency())) {
            target.setCurrency(source.getCurrency());
        }
        if (source.getCheckinHour() != 0) {
            target.setCheckinHour(source.getCheckinHour());
        }
        if (source.getCheckoutHour() != 0) {
            target.setCheckoutHour(source.getCheckoutHour());
        }
    }

    public void patch(Address target, Address source) {
        if (hasText(source.getCountry())) {
            target.setCountry(source.getCountry());
        }
        if (hasText(source.getRegion())) {
            target.setRegion(source.getRegion());
        }
        if (hasText(source.getCity())) {
            target.setCity(source.getCity());
        }
        if (hasText(source.getPostalCode())) {
            target.setPostalCode(source.getPostalCode());
        }
        if (hasText(source.getStreet())) {
            target.setStreet(source.getStreet());
        }
        if (hasText(source.getBuildingNumber())) {
            target.setBuildingNumber(source.getBuildingNumber());
        }
    }
}
