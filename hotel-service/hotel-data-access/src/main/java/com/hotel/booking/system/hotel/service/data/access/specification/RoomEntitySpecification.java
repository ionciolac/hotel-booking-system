package com.hotel.booking.system.hotel.service.data.access.specification;

import com.hotel.booking.system.common.common.enums.RoomType;
import com.hotel.booking.system.hotel.service.data.access.entity.HotelAddressEntity;
import com.hotel.booking.system.hotel.service.data.access.entity.HotelEntity;
import com.hotel.booking.system.hotel.service.data.access.entity.RoomBookingEntity;
import com.hotel.booking.system.hotel.service.data.access.entity.RoomEntity;
import jakarta.persistence.criteria.*;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static com.hotel.booking.system.common.common.utils.AppConstants.SYSTEM_CHECKIN_HOUR;
import static com.hotel.booking.system.common.common.utils.AppConstants.SYSTEM_CHECKOUT_HOUR;
import static com.hotel.booking.system.common.common.utils.DateTimeUtils.addHourAndMinutesToYYYYmmDD;

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

    public static Specification<RoomEntity> filterByIsRoomAvailable(Boolean isRoomAvailable) {
        return (root, query, builder) -> builder.equal(root.get("isRoomAvailable"), isRoomAvailable);
    }

    public static Specification<RoomEntity> filterByCountry(String country) {
        return (root, query, builder) -> {
            Join<RoomEntity, HotelEntity> roomEntityHotelEntityJoin = root.join("hotel", JoinType.INNER);
            Join<HotelEntity, HotelAddressEntity> hotelEntityHotelAddressEntityJoin = roomEntityHotelEntityJoin
                    .join("address", JoinType.INNER);
            return builder.equal(hotelEntityHotelAddressEntityJoin.get("country"), country);
        };
    }

    public static Specification<RoomEntity> filterByCity(String city) {
        return (root, query, builder) -> {
            Join<RoomEntity, HotelEntity> roomEntityHotelEntityJoin = root.join("hotel", JoinType.INNER);
            Join<HotelEntity, HotelAddressEntity> hotelEntityHotelAddressEntityJoin = roomEntityHotelEntityJoin
                    .join("address", JoinType.INNER);
            return builder.equal(hotelEntityHotelAddressEntityJoin.get("city"), city);
        };
    }

    public static Specification<RoomEntity> filterByFromDate(LocalDateTime date) {
        return (root, query, builder) -> {
            var nextDay = addHourAndMinutesToYYYYmmDD(date.plusDays(1), SYSTEM_CHECKOUT_HOUR, 0);
            return filterByDateExcludeBookedRooms(date, root, query, builder, nextDay);
        };

    }

    public static Specification<RoomEntity> filterByToDate(LocalDateTime date) {
        return (root, query, builder) -> {
            var preDay = addHourAndMinutesToYYYYmmDD(date.minusDays(1), SYSTEM_CHECKOUT_HOUR, 0);
            return filterByDateExcludeBookedRooms(date, root, query, builder, preDay);
        };
    }

    public static Specification<RoomEntity> filterByMinPricePerNight(Double minPricePerNight) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("pricePerNight"), minPricePerNight);
    }

    public static Specification<RoomEntity> filterByMaxPricePerNight(Double maxPricePerNight) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("pricePerNight"), maxPricePerNight);
    }

    private static Predicate filterByDateExcludeBookedRooms(LocalDateTime date, Root<RoomEntity> root,
                                                            CriteriaQuery<?> query, CriteriaBuilder builder,
                                                            LocalDateTime prevNextDay) {
        var filterDate = addHourAndMinutesToYYYYmmDD(date, SYSTEM_CHECKIN_HOUR, 0);
        Subquery<UUID> roomBookingEntitySubquery = Objects.requireNonNull(query).subquery(UUID.class);
        Root<RoomBookingEntity> roomBookingEntityRoot = roomBookingEntitySubquery.from(RoomBookingEntity.class);
        Predicate fromDatePredicate = builder.lessThanOrEqualTo(roomBookingEntityRoot.get("fromDate"), prevNextDay);
        Predicate toDatePredicate = builder.greaterThanOrEqualTo(roomBookingEntityRoot.get("toDate"), filterDate);
        Predicate overlappingReservation = builder.and(fromDatePredicate, toDatePredicate);
        roomBookingEntitySubquery.select(roomBookingEntityRoot.get("room")).where(overlappingReservation);
        return builder.and(builder.not(root.get("id").in(roomBookingEntitySubquery)));
    }
}
