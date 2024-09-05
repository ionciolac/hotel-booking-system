package com.hotel.booking.system.customer.service.data.access.adapter;

import com.hotel.booking.system.customer.service.data.access.mapper.CustomerPersistenceMapper;
import com.hotel.booking.system.customer.service.data.access.repository.CustomerRepository;
import com.hotel.booking.system.customer.service.domain.model.Customer;
import com.hotel.booking.system.customer.service.domain.ports.out.CustomerOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class CustomerPersistenceAdapter implements CustomerOutPort {

    // repository
    private final CustomerRepository customerRepository;
    // mappers
    private final CustomerPersistenceMapper customerPersistenceMapper;

    @Override
    public Customer upsertCustomer(Customer customer) {
        var customerEntity = customerPersistenceMapper.toCustomerEntity(customer);
        return customerPersistenceMapper.toCustomer(customerRepository.save(customerEntity));
    }

    @Override
    public void deleteCustomer(UUID id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> getCustomer(UUID id) {
        return customerRepository.findById(id).map(customerPersistenceMapper::toCustomer);
    }

    @Override
    public boolean checkIFCustomerExist(Customer customer) {
        return customerRepository
                .existsByUsernameAndEmailAndPhoneNumber(customer.getUsername(), customer.getEmail(), customer.getPhoneNumber());
    }
}
