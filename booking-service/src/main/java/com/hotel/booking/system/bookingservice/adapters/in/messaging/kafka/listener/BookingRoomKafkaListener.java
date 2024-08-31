package com.hotel.booking.system.bookingservice.adapters.in.messaging.kafka.listener;

import com.hotel.booking.system.bookingservice.ports.in.messaging.BookingRoomListener;
import com.hotel.booking.system.kafka.model.BookingMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class BookingRoomKafkaListener {

    private final BookingRoomListener bookingRoomListener;

    @KafkaListener(groupId = "${kafka-consumer-config.booking-service-consumer-group-id}",
            topics = "${booking-service.created-booking-topic-name}")
    public void createBookingRoomListener(@Payload List<BookingMessage> messages,
                                          @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                                          @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                                          @Header(KafkaHeaders.OFFSET) List<String> offsets) {
        log.info("{} number of create bookings rooms received with keys: {}, partitions: {} and offsets: {}",
                messages.size(), keys, partitions, offsets);
        messages.forEach(bookingRoomListener::consumer);
    }

//    @KafkaListener(groupId = "${kafka-consumer-config.booking-service-consumer-group-id}",
//            topics = "${booking-service.updated-booking-topic-name}")
//    public void updateBookingRoomListener(@Payload List<BookingMessage> messages,
//                                          @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
//                                          @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
//                                          @Header(KafkaHeaders.OFFSET) List<String> offsets) {
//        messages.forEach(bookingRoomListener::consumer);
//    }

    @KafkaListener(groupId = "${kafka-consumer-config.booking-service-consumer-group-id}",
            topics = "${booking-service.removed-booking-topic-name}")
    public void deleteBookingRoomListener(@Payload List<BookingMessage> messages,
                                          @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                                          @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                                          @Header(KafkaHeaders.OFFSET) List<String> offsets) {
        log.info("{} number of delete bookings rooms received with keys: {}, partitions: {} and offsets: {}",
                messages.size(), keys, partitions, offsets);
        messages.forEach(bookingRoomListener::consumer);
    }
}
