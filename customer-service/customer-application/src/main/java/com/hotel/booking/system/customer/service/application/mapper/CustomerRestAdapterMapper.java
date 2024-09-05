package com.hotel.booking.system.customer.service.application.mapper;

import com.hotel.booking.system.customer.service.application.data.req.CreateCustomerRequest;
import com.hotel.booking.system.customer.service.application.data.req.UpdateCustomerRequest;
import com.hotel.booking.system.customer.service.application.data.res.CustomerResponse;
import com.hotel.booking.system.customer.service.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerRestAdapterMapper {

    @Mapping(target = "id", ignore = true)
    Customer toCustomer(CreateCustomerRequest createCustomerRequest);

    Customer toCustomer(UpdateCustomerRequest updateCustomerRequest);

    CustomerResponse toCustomerResponse(Customer customer);
}
