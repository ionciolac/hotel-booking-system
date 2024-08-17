package com.hotel.booking.system.user.service.domain.model;

import com.hotel.booking.system.common.domain.model.Address;
import com.hotel.booking.system.common.domain.model.BaseId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseId {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Address address;
}
