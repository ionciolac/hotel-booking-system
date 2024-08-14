package com.hotel.booking.system.user.service.adapters.in.rest.data.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel.booking.system.common.rest.data.req.address.AddressRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String username;
    private String email;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;
    private AddressRequest address;
}
