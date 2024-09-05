package com.hotel.booking.system.customer.service.application.data.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel.booking.system.common.application.data.res.address.AddressResponse;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

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
