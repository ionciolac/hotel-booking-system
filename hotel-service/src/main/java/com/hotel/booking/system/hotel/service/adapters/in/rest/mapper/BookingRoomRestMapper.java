package com.hotel.booking.system.hotel.service.adapters.in.rest.mapper;

import com.hotel.booking.system.common.rest.data.res.booking.AvailableRoomResponse;
import com.hotel.booking.system.hotel.service.domain.model.AvailableRoom;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface BookingRoomRestMapper {

    AvailableRoomResponse toBookingResponse(AvailableRoom availableRoom);
}
