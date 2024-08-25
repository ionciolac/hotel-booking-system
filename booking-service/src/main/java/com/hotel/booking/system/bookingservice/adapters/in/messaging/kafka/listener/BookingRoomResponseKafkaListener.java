package com.hotel.booking.system.bookingservice.adapters.in.messaging.kafka.listener;

import com.hotel.booking.system.bookingservice.adapters.in.messaging.kafka.mapper.BookingRoomListenerMapper;
import com.hotel.booking.system.bookingservice.ports.in.messaging.BookingRoomResponseListener;
import com.hotel.booking.system.kafka.consumer.KafkaConsumer;
import com.hotel.booking.system.kafka.model.BookingRoomMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BookingRoomResponseKafkaListener implements KafkaConsumer<BookingRoomMessage> {

    private final BookingRoomResponseListener bookingRoomResponseListener;
    private final BookingRoomListenerMapper bookingRoomListenerMapper;

    @KafkaListener(groupId = "${kafka-consumer-config.booking-service-consumer-group-id}",
            topics = "${booking-service.room-booked-topic-name}")
    @Override
    public void receive(@Payload List<BookingRoomMessage> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<String> offsets) {
        messages.forEach(message -> bookingRoomResponseListener
                .roomBooked(bookingRoomListenerMapper.toBooking(message)));
    }
}
