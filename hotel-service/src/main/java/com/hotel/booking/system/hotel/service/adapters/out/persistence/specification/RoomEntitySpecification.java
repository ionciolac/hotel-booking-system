package com.hotel.booking.system.hotel.service.adapters.out.persistence.specification;

import com.hotel.booking.system.common.common.RoomType;
import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.HotelEntity;
import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.RoomEntity;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class RoomEntitySpecification {

    public static Specification<RoomEntity> hotelIdFilter(HotelEntity hotelEntity) {
        return (root, query, builder) -> builder.equal(root.get("hotel"), hotelEntity);
    }

    public static Specification<RoomEntity> floorFilter(Integer floor) {
        return (root, query, builder) -> builder.equal(root.get("floor"), floor);
    }

    public static Specification<RoomEntity> roomTypeFilter(RoomType roomType) {
        return (root, query, builder) -> builder.equal(root.get("roomType"), roomType);
    }
}
