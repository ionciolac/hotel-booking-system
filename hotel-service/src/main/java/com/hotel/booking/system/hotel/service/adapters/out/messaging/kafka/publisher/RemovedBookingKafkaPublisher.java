package com.hotel.booking.system.hotel.service.adapters.out.messaging.kafka.publisher;

import com.hotel.booking.system.hotel.service.config.HotelServiceConfigData;
import com.hotel.booking.system.hotel.service.ports.out.messaging.RemovedBookingPublisher;
import com.hotel.booking.system.kafka.model.RemoveBookingMessage;
import com.hotel.booking.system.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RemovedBookingKafkaPublisher implements RemovedBookingPublisher {

    private final KafkaProducer<String, RemoveBookingMessage> kafkaProducer;
    private final HotelServiceConfigData hotelServiceConfigData;

    @Override
    public void publisher(RemoveBookingMessage removeBookingMessage) {
        var topicName = hotelServiceConfigData.getRemovedBookingTopicName();
        kafkaProducer.send(topicName, removeBookingMessage.bookingId().toString(), removeBookingMessage);
    }
}
