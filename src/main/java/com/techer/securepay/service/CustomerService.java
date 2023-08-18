package com.techer.securepay.service;

import com.techer.securepay.entity.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer saveCustomer(Customer customer);


    List<Customer> getAllCustomer();

    Customer getCustomerByCustomerId(UUID id);

    Customer getCustomerByEmail(String email);

    Customer updateCustomerByCustomerId(UUID id, Customer customer);

    Customer updateCustomerByCustomerEmail(String email, Customer customer);


}
