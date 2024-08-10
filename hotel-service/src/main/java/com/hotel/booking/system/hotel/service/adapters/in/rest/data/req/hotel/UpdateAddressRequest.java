package com.hotel.booking.system.hotel.service.adapters.in.rest.data.req.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAddressRequest extends CreateAddressRequest {

    private UUID id;
}
