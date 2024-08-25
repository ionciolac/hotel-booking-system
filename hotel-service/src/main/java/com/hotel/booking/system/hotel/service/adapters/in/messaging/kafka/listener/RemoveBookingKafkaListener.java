package com.hotel.booking.system.hotel.service.adapters.in.messaging.kafka.listener;

import com.hotel.booking.system.hotel.service.ports.in.messaging.RemoveBookingListener;
import com.hotel.booking.system.kafka.consumer.KafkaConsumer;
import com.hotel.booking.system.kafka.model.RemoveBookingMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class RemoveBookingKafkaListener implements KafkaConsumer<RemoveBookingMessage> {

    private final RemoveBookingListener removeBookingListener;

    @KafkaListener(groupId = "${kafka-consumer-config.hotel-service-consumer-group-id}",
            topics = "${hotel-service.remove-booking-topic-name}")
    @Override
    public void receive(@Payload List<RemoveBookingMessage> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<String> offsets) {
        messages.forEach(removeBookingListener::removeBooking);
    }
}
