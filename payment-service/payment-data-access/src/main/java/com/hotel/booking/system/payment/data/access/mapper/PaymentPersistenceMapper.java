package com.hotel.booking.system.payment.data.access.mapper;

import com.hotel.booking.system.payment.data.access.entity.PaymentEntity;
import com.hotel.booking.system.payment.domain.model.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentPersistenceMapper {

    Payment toPayment(PaymentEntity paymentEntity);

    PaymentEntity toPaymentEntity(Payment payment);
}
