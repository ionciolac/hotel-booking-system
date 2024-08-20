package com.hotel.booking.system.hotel.service.adapters.in.messaging.listner;


import com.hotel.booking.system.common.messaging.BookRoomRequest;
import com.hotel.booking.system.hotel.service.adapters.in.messaging.mapper.BookingRoomMessagingMapper;
import com.hotel.booking.system.hotel.service.ports.in.messaging.BookingRoomListenerPort;
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
public class BookingRoomListener {

    private final BookingRoomListenerPort bookingRoomListenerPort;
    private final BookingRoomMessagingMapper bookingRoomMessagingMapper;

    //TODO temporary method to book room's will be replaced with kafka linter
//    @Scheduled(cron = "*/10 * * * * *")
    void execute() {
        log.info("start cron");
        var roomBookingRequest = BookRoomRequest.builder()
                .roomId(fromString("cbab7043-030c-42cd-b700-daa93338c719"))
                .userId(fromString("1d48ce6b-82fd-4748-8185-3b2e5988dd51"))
                .bookingId(fromString("1efb108c-7c43-4057-b229-9da8d9652ce4"))
                .fromDate(LocalDateTime.of(2024, 10, 7, 0, 0))
                .toDate(LocalDateTime.of(2024, 10, 10, 0, 0))
                .build();
        var roomBooking = bookingRoomMessagingMapper.toRoomBooking(roomBookingRequest);
        bookingRoomListenerPort.bookRoom(roomBooking);
        log.info("stop cron");
    }
}
