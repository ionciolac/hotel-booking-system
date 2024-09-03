package com.hotel.booking.system.hotel.service.messaging.publisher;

import com.hotel.booking.system.hotel.service.domain.ports.out.messaging.BookingPublisher;
import com.hotel.booking.system.kafka.model.BookingMessage;
import com.hotel.booking.system.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookingKafkaPublisher implements BookingPublisher {

    private final KafkaProducer<String, BookingMessage> kafkaProducer;

    @Override
    public void publish(String topic, String key, BookingMessage bookingMessage) {
        kafkaProducer.send(topic, key, bookingMessage);
    }
}
