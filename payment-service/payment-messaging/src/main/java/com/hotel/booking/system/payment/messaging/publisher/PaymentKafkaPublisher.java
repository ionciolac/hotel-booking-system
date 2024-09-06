package com.hotel.booking.system.payment.messaging.publisher;

import com.hotel.booking.system.payment.domain.ports.out.messaging.PaymentPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentKafkaPublisher implements PaymentPublisher {
}
