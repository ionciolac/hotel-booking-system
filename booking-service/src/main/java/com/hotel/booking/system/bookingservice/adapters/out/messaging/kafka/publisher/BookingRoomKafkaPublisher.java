package com.hotel.booking.system.bookingservice.adapters.out.messaging.kafka.publisher;

import com.hotel.booking.system.bookingservice.ports.out.messaging.BookingRoomPublisher;
import com.hotel.booking.system.kafka.model.BookingMessage;
import com.hotel.booking.system.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookingRoomKafkaPublisher implements BookingRoomPublisher {

    private final KafkaProducer<String, BookingMessage> kafkaProducer;

    @Override
    public void publish(String topic, String key, BookingMessage bookingMessage) {
        kafkaProducer.send(topic, key, bookingMessage);
    }
}
