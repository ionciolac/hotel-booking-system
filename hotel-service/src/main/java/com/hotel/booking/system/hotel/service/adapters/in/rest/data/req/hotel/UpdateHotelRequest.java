package com.hotel.booking.system.hotel.service.adapters.in.rest.data.req.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;


@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateHotelRequest extends CreateHotelRequest {

    private UUID id;
}
