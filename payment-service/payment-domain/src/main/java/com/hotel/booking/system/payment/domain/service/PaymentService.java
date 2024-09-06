package com.hotel.booking.system.payment.domain.service;

import com.hotel.booking.system.payment.domain.ports.in.messaging.PaymentListener;
import com.hotel.booking.system.payment.domain.ports.out.messaging.PaymentPublisher;
import com.hotel.booking.system.payment.domain.ports.out.persistence.PaymentPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService implements PaymentListener {

    private final PaymentPersistence paymentPersistence;
    private final PaymentPublisher paymentPublisher;

}
