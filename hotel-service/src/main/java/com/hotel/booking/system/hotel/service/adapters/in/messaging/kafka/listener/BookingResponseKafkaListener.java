package com.hotel.booking.system.hotel.service.adapters.in.messaging.kafka.listener;

import com.hotel.booking.system.hotel.service.adapters.in.messaging.kafka.mapper.BookingListenerMapper;
import com.hotel.booking.system.hotel.service.ports.in.messaging.BookingResponseListener;
import com.hotel.booking.system.kafka.consumer.KafkaConsumer;
import com.hotel.booking.system.kafka.model.BookingRoomMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@EnableScheduling
@Component
public class BookingResponseKafkaListener implements KafkaConsumer<BookingRoomMessage> {

    private final BookingResponseListener bookingResponseListener;
    private final BookingListenerMapper bookingListenerMapper;

    @KafkaListener(groupId = "${kafka-consumer-config.hotel-service-consumer-group-id}",
            topics = "${hotel-service.book-room-topic-name}")
    @Override
    public void receive(@Payload List<BookingRoomMessage> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<String> offsets) {
        messages.forEach(message -> bookingResponseListener.bookRoom(bookingListenerMapper.toRoomBooking(message)));
    }
}
