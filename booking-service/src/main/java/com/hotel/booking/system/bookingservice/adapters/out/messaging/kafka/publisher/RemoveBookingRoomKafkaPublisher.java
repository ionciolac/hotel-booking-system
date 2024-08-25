package com.hotel.booking.system.bookingservice.adapters.out.messaging.kafka.publisher;

import com.hotel.booking.system.bookingservice.config.BookingServiceConfigData;
import com.hotel.booking.system.bookingservice.ports.out.messaging.RemoveBookingRoomPublisher;
import com.hotel.booking.system.kafka.model.RemoveBookingMessage;
import com.hotel.booking.system.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RemoveBookingRoomKafkaPublisher implements RemoveBookingRoomPublisher {

    private final KafkaProducer<String, RemoveBookingMessage> kafkaProducer;
    private final BookingServiceConfigData bookingServiceConfigData;

    @Override
    public void publish(RemoveBookingMessage removeBookingMessage) {
        var topicName = bookingServiceConfigData.getRemoveBookingTopicName();
        kafkaProducer.send(topicName, removeBookingMessage.roomBookingId().toString(), removeBookingMessage);
    }
}
