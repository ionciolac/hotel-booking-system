package com.hotel.booking.system.customer.service.domain.ports.in;

import com.hotel.booking.system.customer.service.domain.model.Customer;

import java.util.UUID;

public interface CustomerInPort {

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(UUID id);

    Customer getCustomer(UUID id);
}
