package com.hotel.booking.system.user.service.adapters.in.rest.data.req;

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
public class UpdateUserRequest extends CreateUserRequest {

    private UUID id;
}
