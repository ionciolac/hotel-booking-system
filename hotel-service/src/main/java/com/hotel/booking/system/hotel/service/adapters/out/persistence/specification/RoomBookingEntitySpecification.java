package com.hotel.booking.system.hotel.service.adapters.out.persistence.specification;

import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.RoomBookingEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.UUID;

public class RoomBookingEntitySpecification {

    public static Specification<RoomBookingEntity> roomIdFilter(UUID roomId) {
        return (root, query, builder) -> builder.equal(root.get("room").get("id"), roomId);
    }

    public static Specification<RoomBookingEntity> fromDateToDateFilter(LocalDateTime fromDate, LocalDateTime toDate) {
        return (root, query, builder) -> {
            Predicate fromDatePredicate = builder.lessThanOrEqualTo(root.get("fromDate"), toDate);
            Predicate toDatePredicate = builder.greaterThanOrEqualTo(root.get("toDate"), fromDate);
            return builder.and(fromDatePredicate, toDatePredicate);
        };
    }
}
