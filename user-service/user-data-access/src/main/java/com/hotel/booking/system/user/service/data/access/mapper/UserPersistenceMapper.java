package com.hotel.booking.system.user.service.data.access.mapper;

import com.hotel.booking.system.user.service.data.access.entity.UserEntity;
import com.hotel.booking.system.user.service.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    User toUser(UserEntity userEntity);

    UserEntity toUserEntity(User user);
}
