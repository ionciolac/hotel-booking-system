package com.hotel.booking.system.hotel.service.adapters.out.persistence.mapper;

import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.RoomEntity;
import com.hotel.booking.system.hotel.service.domain.model.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomPersistenceMapper {

    Room toRoom(RoomEntity roomEntity);

    RoomEntity toRoomEntity(Room room);
}
