package com.hotel.booking.system.kafka.model;

import com.hotel.booking.system.common.common.BookingStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CreateBookingMessage(UUID roomId, UUID userId, UUID bookingId, UUID roomBookingId, UUID hotelId,
                                   LocalDateTime fromDate, LocalDateTime toDate, BookingStatus status) {

}
