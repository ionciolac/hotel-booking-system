package com.hotel.booking.system.hotel.service.adapters.out.messaging.kafka.publisher;

import com.hotel.booking.system.common.common.BookingStatus;
import com.hotel.booking.system.hotel.service.adapters.out.messaging.kafka.mapper.BookingPublisherMapper;
import com.hotel.booking.system.hotel.service.config.HotelServiceConfigData;
import com.hotel.booking.system.hotel.service.domain.model.RoomBooking;
import com.hotel.booking.system.hotel.service.ports.out.messaging.CreateBookingPublisher;
import com.hotel.booking.system.kafka.model.CreateBookingMessage;
import com.hotel.booking.system.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateBookingKafkaPublisher implements CreateBookingPublisher {

    private final KafkaProducer<String, CreateBookingMessage> kafkaProducer;
    private final BookingPublisherMapper bookingPublisherMapper;
    private final HotelServiceConfigData hotelServiceConfigData;


    @Override
    public void publisher(RoomBooking roomBooking, BookingStatus status) {
        var bookRoomRequest = getBookRoomRequest(roomBooking, status);
        var topicName = hotelServiceConfigData.getRoomBookedTopicName();
        kafkaProducer.send(topicName, roomBooking.getBookingId().toString(), bookRoomRequest);
    }

    private CreateBookingMessage getBookRoomRequest(RoomBooking roomBooking, BookingStatus status) {
        CreateBookingMessage result = null;
        switch (status) {
            case ROOM_IS_ALREADY_BOOKED -> result = bookingPublisherMapper
                    .toCreateBookingMessageROOM_IS_ALREADY_BOOKED(roomBooking);
            case ROOM_BOOKED -> result = bookingPublisherMapper
                    .toCreateBookingMessageROOM_BOOKED(roomBooking);
        }
        return result;
    }
}
