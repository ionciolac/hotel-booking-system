package com.hotel.booking.system.user.service.adapters.out.persistence.mapper;


import com.hotel.booking.system.user.service.adapters.out.persistence.entity.UserEntity;
import com.hotel.booking.system.user.service.domain.model.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    User toUser(UserEntity userEntity);

    UserEntity toUserEntity(User user);
}
