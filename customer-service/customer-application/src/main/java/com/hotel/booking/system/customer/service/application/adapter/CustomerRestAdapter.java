package com.hotel.booking.system.customer.service.application.adapter;

import com.hotel.booking.system.common.application.data.res.DeletedResponse;
import com.hotel.booking.system.customer.service.application.data.req.CreateCustomerRequest;
import com.hotel.booking.system.customer.service.application.data.req.UpdateCustomerRequest;
import com.hotel.booking.system.customer.service.application.data.res.CustomerResponse;
import com.hotel.booking.system.customer.service.application.mapper.CustomerRestAdapterMapper;
import com.hotel.booking.system.customer.service.domain.ports.in.CustomerInPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.hotel.booking.system.common.common.utils.AppCommonMessages.CONTROLLER_DELETE_MESSAGE_RESPONSE_MESSAGE;
import static com.hotel.booking.system.common.common.utils.AppCommonMessages.CUSTOMER;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RequestMapping("customer")
@RestController
public class CustomerRestAdapter {

    private final CustomerInPort customerInPort;
    private final CustomerRestAdapterMapper customerRestAdapterMapper;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CreateCustomerRequest req) {
        var customer = customerInPort.createCustomer(customerRestAdapterMapper.toCustomer(req));
        return ResponseEntity.status(CREATED).body(customerRestAdapterMapper.toCustomerResponse(customer));
    }

    @PutMapping
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody UpdateCustomerRequest req) {
        var customer = customerInPort.updateCustomer(customerRestAdapterMapper.toCustomer(req));
        return ResponseEntity.status(OK).body(customerRestAdapterMapper.toCustomerResponse(customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletedResponse> deleteCustomer(@PathVariable("id") UUID id) {
        customerInPort.deleteCustomer(id);
        var msg = format(CONTROLLER_DELETE_MESSAGE_RESPONSE_MESSAGE, CUSTOMER, id);
        return ResponseEntity.status(OK).body(new DeletedResponse(Boolean.TRUE, msg));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("id") UUID id) {
        var customer = customerInPort.getCustomer(id);
        return ResponseEntity.status(OK).body(customerRestAdapterMapper.toCustomerResponse(customer));
    }
}
