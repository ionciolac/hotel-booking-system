package com.hotel.booking.system.hotel.service.adapters.out.persistence.mapper;

import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.RoomBookingEntity;
import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.RoomEntity;
import com.hotel.booking.system.hotel.service.domain.model.Room;
import com.hotel.booking.system.hotel.service.domain.model.RoomBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RoomPersistenceMapper {

    @Named("roomRoomInRoomBookings")
    @Mapping(target = "room", ignore = true)
    RoomBooking roomBookingEntityToRoomBooking(RoomBookingEntity roomBookingEntity);

    @Mapping(target = "roomBookings", qualifiedByName = "roomRoomInRoomBookings")
    Room toRoom(RoomEntity roomEntity);

    RoomEntity toRoomEntity(Room room);
}
