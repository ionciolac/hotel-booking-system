package com.hotel.booking.system.common.rest.data.res.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    private String country;
    private String region;
    private String city;
    @JsonProperty("postal_code")
    private String postalCode;
    private String street;
    @JsonProperty("building_number")
    private String buildingNumber;
}
