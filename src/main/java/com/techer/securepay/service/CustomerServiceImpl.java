package com.techer.securepay.service;


import com.techer.securepay.exception.CustomerAlreadyExistsException;
import com.techer.securepay.exception.CustomerNotFoundException;
import com.techer.securepay.entity.Customer;
import com.techer.securepay.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;


    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }

    @Override
    public Customer saveCustomer(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CustomerAlreadyExistsException("Customer already registered with given email: " + customer.getEmail());
        }
        return customerRepository.save(customer);
    }


    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerByCustomerId(UUID id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with given ID: " + id));
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with given email: " + email));
    }

    @Override
    public Customer updateCustomerByCustomerId(UUID id, Customer customer) {
        Customer foundCustomer = getCustomerByCustomerId(id);
        foundCustomer.setFirstName(customer.getFirstName());
        foundCustomer.setLastName(customer.getLastName());
        foundCustomer.setPaymentList((customer.getPaymentList()));
        foundCustomer.setPhoneNo(customer.getPhoneNo());
        foundCustomer.setAddress(customer.getAddress());
        foundCustomer.setCountry(customer.getCountry());
        foundCustomer.setCity(customer.getCity());
        foundCustomer.setZipCode(customer.getZipCode());
        foundCustomer.setAddress(customer.getAddress());
        return customerRepository.save(foundCustomer);

    }

    @Override
    public Customer updateCustomerByCustomerEmail(String email, Customer customer) {
        Customer foundCustomer = getCustomerByEmail(email);
        foundCustomer.setFirstName(customer.getFirstName());
        foundCustomer.setLastName(customer.getLastName());
        foundCustomer.setPhoneNo(customer.getPhoneNo());
        foundCustomer.setAddress(customer.getAddress());
        foundCustomer.setCountry(customer.getCountry());
        foundCustomer.setCity(customer.getCity());
        foundCustomer.setZipCode(customer.getZipCode());
        foundCustomer.setAddress(customer.getAddress());
        return customerRepository.save(foundCustomer);
    }

}
