package com.hotel.booking.system.user.service.adapters.in.rest.mapper;


import com.hotel.booking.system.user.service.adapters.in.rest.data.req.CreateUserRequest;
import com.hotel.booking.system.user.service.adapters.in.rest.data.req.UpdateUserRequest;
import com.hotel.booking.system.user.service.adapters.in.rest.data.res.UserResponse;
import com.hotel.booking.system.user.service.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserRestAdapterMapper {

    @Mapping(target = "id", ignore = true)
    User toUser(CreateUserRequest createUserRequest);

    User toUser(UpdateUserRequest updateUserRequest);

    UserResponse toUserResponse(User user);
}
