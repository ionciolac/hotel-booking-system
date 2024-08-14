package com.hotel.booking.system.user.service.adapters.in.rest.data.req;

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
public class UpdateUserRequest extends CreateUserRequest {

    private UUID id;
}
