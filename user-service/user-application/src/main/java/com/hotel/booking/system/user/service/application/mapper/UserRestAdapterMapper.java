package com.hotel.booking.system.user.service.application.mapper;

import com.hotel.booking.system.user.service.application.data.req.CreateUserRequest;
import com.hotel.booking.system.user.service.application.data.req.UpdateUserRequest;
import com.hotel.booking.system.user.service.application.data.res.UserResponse;
import com.hotel.booking.system.user.service.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRestAdapterMapper {

    @Mapping(target = "id", ignore = true)
    User toUser(CreateUserRequest createUserRequest);

    User toUser(UpdateUserRequest updateUserRequest);

    UserResponse toUserResponse(User user);
}
