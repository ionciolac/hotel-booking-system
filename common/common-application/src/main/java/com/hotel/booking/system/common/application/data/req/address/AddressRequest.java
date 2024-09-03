package com.hotel.booking.system.common.application.data.req.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    private String country;
    private String region;
    private String city;
    @JsonProperty("postal_code")
    private String postalCode;
    private String street;
    @JsonProperty("building_number")
    private String buildingNumber;
}
