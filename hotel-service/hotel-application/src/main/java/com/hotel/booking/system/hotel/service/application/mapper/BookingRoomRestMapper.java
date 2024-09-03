package com.hotel.booking.system.hotel.service.application.mapper;

import com.hotel.booking.system.common.application.data.res.booking.AvailableRoomResponse;
import com.hotel.booking.system.hotel.service.domain.model.AvailableRoom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingRoomRestMapper {

    AvailableRoomResponse toBookingResponse(AvailableRoom availableRoom);
}
