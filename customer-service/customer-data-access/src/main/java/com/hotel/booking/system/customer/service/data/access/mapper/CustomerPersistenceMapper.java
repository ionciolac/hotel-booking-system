package com.hotel.booking.system.customer.service.data.access.mapper;

import com.hotel.booking.system.customer.service.data.access.entity.CustomerEntity;
import com.hotel.booking.system.customer.service.domain.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerPersistenceMapper {

    Customer toCustomer(CustomerEntity customerEntity);

    CustomerEntity toCustomerEntity(Customer customer);
}
