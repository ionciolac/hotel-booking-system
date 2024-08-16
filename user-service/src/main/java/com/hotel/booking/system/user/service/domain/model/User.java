package com.hotel.booking.system.user.service.domain.model;

import com.hotel.booking.system.common.domain.model.Address;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Address address;

    public void generateID() {
        this.id = randomUUID();
    }
}
