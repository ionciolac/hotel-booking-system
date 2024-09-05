package com.hotel.booking.system.customer.service.domain.ports.out;

import com.hotel.booking.system.customer.service.domain.model.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerOutPort {

    Customer upsertCustomer(Customer customer);

    void deleteCustomer(UUID id);

    Optional<Customer> getCustomer(UUID id);

    boolean checkIFCustomerExist(Customer customer);
}
