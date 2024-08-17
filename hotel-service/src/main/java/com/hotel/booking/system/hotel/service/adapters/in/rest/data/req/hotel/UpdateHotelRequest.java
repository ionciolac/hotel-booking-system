package com.hotel.booking.system.hotel.service.adapters.in.rest.data.req.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;


@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateHotelRequest extends CreateHotelRequest {

    private UUID id;
}
