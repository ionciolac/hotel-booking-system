package com.hotel.booking.system.hotel.service.adapters.in.messaging.kafka.listener;

import com.hotel.booking.system.hotel.service.ports.in.messaging.BookingListener;
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
public class BookingKafkaListener {

    private final BookingListener bookingListener;

    @KafkaListener(groupId = "${kafka-consumer-config.hotel-service-consumer-group-id}",
            topics = "${hotel-service.create-booking-topic-name}")
    public void createBookingListener(@Payload List<BookingMessage> messages,
                                      @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                                      @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                                      @Header(KafkaHeaders.OFFSET) List<String> offsets) {
        log.info("{} number of create bookings received with keys: {}, partitions: {} and offsets: {}",
                messages.size(), keys, partitions, offsets);
        messages.forEach(bookingListener::consumer);
    }

    @KafkaListener(groupId = "${kafka-consumer-config.hotel-service-consumer-group-id}",
            topics = "${hotel-service.update-booking-topic-name}")
    public void updateBookingListener(@Payload List<BookingMessage> messages,
                                      @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                                      @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                                      @Header(KafkaHeaders.OFFSET) List<String> offsets) {
        log.info("{} number of update bookings received with keys: {}, partitions: {} and offsets: {}",
                messages.size(), keys, partitions, offsets);
        messages.forEach(bookingListener::consumer);
    }

    @KafkaListener(groupId = "${kafka-consumer-config.hotel-service-consumer-group-id}",
            topics = "${hotel-service.remove-booking-topic-name}")
    public void deleteBookingListener(@Payload List<BookingMessage> messages,
                                      @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                                      @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                                      @Header(KafkaHeaders.OFFSET) List<String> offsets) {
        log.info("{} number of delete bookings received with keys: {}, partitions: {} and offsets: {}",
                messages.size(), keys, partitions, offsets);
        messages.forEach(bookingListener::consumer);
    }
}
