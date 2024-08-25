package com.hotel.booking.system.bookingservice.adapters.out.messaging.kafka.publisher;

import com.hotel.booking.system.bookingservice.adapters.out.messaging.kafka.mapper.BookingRoomPublisherMapper;
import com.hotel.booking.system.bookingservice.config.BookingServiceConfigData;
import com.hotel.booking.system.bookingservice.domain.model.Booking;
import com.hotel.booking.system.bookingservice.ports.out.messaging.CreateBookingRoomPublisher;
import com.hotel.booking.system.kafka.model.BookingRoomMessage;
import com.hotel.booking.system.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateBookingRoomKafkaPublisher implements CreateBookingRoomPublisher {

    private final KafkaProducer<String, BookingRoomMessage> kafkaProducer;
    private final BookingRoomPublisherMapper bookingRoomPublisherMapper;
    private final BookingServiceConfigData bookingServiceConfigData;

    @Override
    public void publish(Booking booking) {
        var bookRoomRequest = bookingRoomPublisherMapper.toBookingRoomMessage(booking);
        var topicName = bookingServiceConfigData.getBookRoomTopicName();
        kafkaProducer.send(topicName, bookRoomRequest.bookingId().toString(), bookRoomRequest);
    }
}
