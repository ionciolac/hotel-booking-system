package com.hotel.booking.system.common.messaging;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookRoomRequest {

    private UUID roomId;
    private UUID userId;
    private UUID bookingId;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
