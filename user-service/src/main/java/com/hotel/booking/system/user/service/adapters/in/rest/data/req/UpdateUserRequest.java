package com.hotel.booking.system.user.service.adapters.in.rest.data.req;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest extends CreateUserRequest {

    private UUID id;
}
