package com.hotel.booking.system.customer.service.domain.model;

import com.hotel.booking.system.common.domain.Address;
import com.hotel.booking.system.common.domain.BaseId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

import static org.springframework.util.StringUtils.hasText;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseId {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Address address;
    private String customerCurrency;

    public void patch(Customer target, Customer source) {
        if (hasText(source.getFirstName())) {
            target.setFirstName(source.getFirstName());
        }
        if (hasText(source.getLastName())) {
            target.setLastName(source.getLastName());
        }
        if (hasText(source.getUsername())) {
            target.setUsername(source.getUsername());
        }
        if (hasText(source.getEmail())) {
            target.setEmail(source.getEmail());
        }
        if (hasText(source.getPhoneNumber())) {
            target.setPhoneNumber(source.getPhoneNumber());
        }
        if (source.getDateOfBirth() != null) {
            target.setDateOfBirth(source.getDateOfBirth());
        }
        if (source.getAddress() != null) {
            patch(target.getAddress(), source.getAddress());
        }
        if (hasText(source.getCustomerCurrency())) {
            target.setCustomerCurrency(source.getCustomerCurrency());
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
