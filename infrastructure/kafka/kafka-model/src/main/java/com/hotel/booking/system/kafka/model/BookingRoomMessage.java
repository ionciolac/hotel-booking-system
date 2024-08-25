package com.hotel.booking.system.kafka.model;

import com.hotel.booking.system.common.common.BookingStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record BookingRoomMessage(UUID roomId, UUID userId, UUID bookingId, UUID roomBookingId, LocalDateTime fromDate,
                                 LocalDateTime toDate, BookingStatus status) {

}
