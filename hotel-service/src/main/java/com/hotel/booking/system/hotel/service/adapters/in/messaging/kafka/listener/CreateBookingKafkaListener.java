package com.hotel.booking.system.hotel.service.adapters.in.messaging.kafka.listener;

import com.hotel.booking.system.hotel.service.adapters.in.messaging.kafka.mapper.BookingListenerMapper;
import com.hotel.booking.system.hotel.service.ports.in.messaging.CreateBookingListener;
import com.hotel.booking.system.kafka.consumer.KafkaConsumer;
import com.hotel.booking.system.kafka.model.CreateBookingMessage;
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
public class CreateBookingKafkaListener implements KafkaConsumer<CreateBookingMessage> {

    private final CreateBookingListener createBookingListener;
    private final BookingListenerMapper bookingListenerMapper;

    @KafkaListener(groupId = "${kafka-consumer-config.hotel-service-consumer-group-id}",
            topics = "${hotel-service.book-room-topic-name}")
    @Override
    public void receive(@Payload List<CreateBookingMessage> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<String> offsets) {
        messages.forEach(message -> createBookingListener.createBooking(bookingListenerMapper.toRoomBooking(message)));
    }
}
