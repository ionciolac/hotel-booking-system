package com.hotel.booking.system.customer.service.domain.service;

import com.hotel.booking.system.common.common.exception.AlreadyExistException;
import com.hotel.booking.system.common.common.exception.NotFoundException;
import com.hotel.booking.system.customer.service.domain.model.Customer;
import com.hotel.booking.system.customer.service.domain.ports.in.CustomerInPort;
import com.hotel.booking.system.customer.service.domain.ports.out.CustomerOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.hotel.booking.system.common.common.utils.AppCommonMessages.*;
import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class CustomerService implements CustomerInPort {

    private final CustomerOutPort customerOutPort;

    @Override
    public Customer createCustomer(Customer customer) {
        checkIfCustomerIsUnique(customer);
        var customerAddress = customer.getAddress();
        customerAddress.generateID();
        customer.generateID();
        customer.setAddress(customerAddress);
        return customerOutPort.upsertCustomer(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        var customerId = customer.getId();
        var dbCustomer = getDBCustomer(customerId);
        dbCustomer.patch(dbCustomer, customer);
        return customerOutPort.upsertCustomer(dbCustomer);
    }

    @Override
    public void deleteCustomer(UUID id) {
        getDBCustomer(id);
        customerOutPort.deleteCustomer(id);
    }

    @Override
    public Customer getCustomer(UUID id) {
        return getDBCustomer(id);
    }

    private Customer getDBCustomer(UUID id) {
        var customer = customerOutPort.getCustomer(id);
        if (customer.isPresent())
            return customer.get();
        else
            throw new NotFoundException(format(SERVICE_OBJECT_WAS_NOT_FOUND_IN_DB_MESSAGE, CUSTOMER, id));
    }

    private void checkIfCustomerIsUnique(Customer customer) {
        if (customerOutPort.checkIFCustomerExist(customer))
            throw new AlreadyExistException(SERVICE_CUSTOMER_ALREADY_EXIST_MESSAGE);
    }
}
