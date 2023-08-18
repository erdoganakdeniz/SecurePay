package com.techer.securepay.mapper;

import com.techer.securepay.dto.request.CreateCustomerRequest;
import com.techer.securepay.dto.response.CustomerResponse;
import com.techer.securepay.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataMapper {


    public Customer customerRequestToCustomer(CreateCustomerRequest customerRequest) {
        return new Customer.Builder().email(customerRequest.email())
                .password(customerRequest.password())
                .identityNumber(customerRequest.identityNumber())
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .phoneNo(customerRequest.phoneNo())
                .country(customerRequest.country())
                .city(customerRequest.city())
                .zipCode(customerRequest.zipCode())
                .address(customerRequest.address())
                .build();
    }

    public CustomerResponse customerToCustomerResponse(Customer customer) {
        return new CustomerResponse(customer.getId(),
                customer.getEmail(),
                customer.getPhoneNo(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getIdentityNumber(),
                customer.getCountry(),
                customer.getCity(),
                customer.getZipCode(),
                customer.getAddress());
    }
}
