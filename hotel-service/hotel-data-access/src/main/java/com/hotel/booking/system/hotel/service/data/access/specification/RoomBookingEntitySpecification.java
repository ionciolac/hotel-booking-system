package com.hotel.booking.system.hotel.service.data.access.specification;

import com.hotel.booking.system.hotel.service.data.access.entity.RoomBookingEntity;
import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.UUID;

@UtilityClass
public class RoomBookingEntitySpecification {

    public static Specification<RoomBookingEntity> roomIdFilter(UUID roomId) {
        return (root, query, builder) -> builder.equal(root.get("room").get("id"), roomId);
    }

    public static Specification<RoomBookingEntity> userIdFilter(UUID userId) {
        return (root, query, builder) -> builder.notEqual(root.get("userId"), userId);
    }

    public static Specification<RoomBookingEntity> fromDateToDateFilter(LocalDateTime fromDate, LocalDateTime toDate) {
        return (root, query, builder) -> {
            Predicate fromDatePredicate = builder.lessThanOrEqualTo(root.get("fromDate"), toDate);
            Predicate toDatePredicate = builder.greaterThanOrEqualTo(root.get("toDate"), fromDate);
            return builder.and(fromDatePredicate, toDatePredicate);
        };
    }
}
