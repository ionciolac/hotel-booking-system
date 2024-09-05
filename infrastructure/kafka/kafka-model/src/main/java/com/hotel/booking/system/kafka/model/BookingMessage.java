package com.hotel.booking.system.kafka.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record BookingMessage(UUID bookingId, UUID roomBookingId, UUID roomId, UUID customerId,
                             LocalDateTime fromDate, LocalDateTime toDate, BookingMessageStatus status) {
}
