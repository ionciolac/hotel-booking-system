package com.hotel.booking.system.hotel.service.adapters.in.messaging.listner;


import com.hotel.booking.system.common.messaging.RoomBookingRequest;
import com.hotel.booking.system.hotel.service.adapters.in.messaging.mapper.RoomBookingMessagingMapper;
import com.hotel.booking.system.hotel.service.ports.in.messaging.BookRoomListener;
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
public class RoomBookingListener {

    private final BookRoomListener bookRoomListener;
    private final RoomBookingMessagingMapper roomBookingMessagingMapper;

    //TODO temporary method to book room's will be replaced with kafka linter
//    @Scheduled(cron = "*/10 * * * * *")
    void execute() {
        log.info("start cron");
        var roomBookingRequest = RoomBookingRequest.builder()
                .roomId(fromString("1efb108c-7c43-4057-b229-9da8d9652ce2"))
                .userId(fromString("1efb108c-7c43-4057-b229-9da8d9652ce3"))
                .bookingId(fromString("1efb108c-7c43-4057-b229-9da8d9652ce4"))
                .fromDate(LocalDateTime.of(2024, 9, 20, 0, 0))
                .toDate(LocalDateTime.of(2024, 9, 21, 0, 0))
                .build();
        var roomBooking = roomBookingMessagingMapper.toRoomBooking(roomBookingRequest);
        bookRoomListener.bookRoom(roomBooking);
        log.info("stop cron");
    }
}
