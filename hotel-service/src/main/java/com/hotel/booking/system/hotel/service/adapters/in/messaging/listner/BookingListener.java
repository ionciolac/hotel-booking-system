package com.hotel.booking.system.hotel.service.adapters.in.messaging.listner;


import com.hotel.booking.system.common.messaging.RoomBookingRequest;
import com.hotel.booking.system.hotel.service.adapters.in.messaging.mapper.BookingMessagingMapper;
import com.hotel.booking.system.hotel.service.ports.in.messaging.BookingListenerPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static java.util.UUID.fromString;

@Slf4j
@RequiredArgsConstructor
@EnableScheduling
@Component
public class BookingListener {

    private final BookingListenerPort bookingListenerPort;
    private final BookingMessagingMapper bookingMessagingMapper;

    //TODO temporary method to book room's will be replaced with kafka linter
//    @Scheduled(cron = "*/10 * * * * *")
    void execute() {
        log.info("start cron");
        var roomBookingRequest = RoomBookingRequest.builder()
                .roomId(fromString("812d6453-0052-45df-83c4-e3a523302ce3"))
                .userId(fromString("1d48ce6b-82fd-4748-8185-3b2e5988dd51"))
                .bookingId(fromString("1efb108c-7c43-4057-b229-9da8d9652ce4"))
                .fromDate(LocalDateTime.of(2024, 9, 7, 0, 0))
                .toDate(LocalDateTime.of(2024, 9, 13, 0, 0))
                .build();
        var roomBooking = bookingMessagingMapper.toRoomBooking(roomBookingRequest);
        bookingListenerPort.bookRoom(roomBooking);
        log.info("stop cron");
    }
}
