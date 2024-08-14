package com.hotel.booking.system.user.service.adapters.in.rest.data.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel.booking.system.common.rest.data.res.address.AddressResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private UUID id;
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
    private AddressResponse address;
}
