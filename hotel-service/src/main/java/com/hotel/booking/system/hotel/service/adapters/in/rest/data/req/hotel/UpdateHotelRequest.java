package com.hotel.booking.system.hotel.service.adapters.in.rest.data.req.hotel;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;


@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateHotelRequest extends CreateHotelRequest {

    private UUID id;
}
