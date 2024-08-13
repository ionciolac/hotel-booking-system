package com.hotel.booking.system.user.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Builder
@Data
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
    private UserAddress address;

    public void generateID() {
        this.id = randomUUID();
    }
}
