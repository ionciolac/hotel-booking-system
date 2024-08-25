package com.hotel.booking.system.kafka.model;

import lombok.Builder;

import java.util.UUID;

@Builder
public record RemoveBookingMessage(UUID bookingId, UUID roomId, UUID roomBookingId, BookingRemoveStatus removeStatus) {
}
