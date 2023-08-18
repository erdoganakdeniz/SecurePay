package com.techer.securepay.controller;

import com.techer.securepay.dto.request.CreateCustomerRequest;
import com.techer.securepay.dto.request.PaymentCardRequest;
import com.techer.securepay.dto.response.CustomerResponse;
import com.techer.securepay.dto.response.PaymentCardResponse;
import com.techer.securepay.exception.CustomerAlreadyExistsException;
import com.techer.securepay.exception.PaymentCardAlreadyRegisteredException;
import com.techer.securepay.mapper.CustomerDataMapper;
import com.techer.securepay.mapper.PaymentCardDataMapper;
import com.techer.securepay.service.CustomerService;
import com.techer.securepay.service.PaymentCardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/customer")
@Tag(name = "Customer API")
public class CustomerController {

    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;
    private final CustomerDataMapper customerDataMapper;
    private final PaymentCardService paymentCardService;
    private final PaymentCardDataMapper paymentCardDataMapper;


    public CustomerController(CustomerService customerService, CustomerDataMapper customerDataMapper, PaymentCardService paymentCardService, PaymentCardDataMapper paymentCardDataMapper) {
        this.customerService = customerService;
        this.customerDataMapper = customerDataMapper;
        this.paymentCardService = paymentCardService;
        this.paymentCardDataMapper = paymentCardDataMapper;
    }

    @PostMapping
    public ResponseEntity<?> saveCustomer(@RequestBody CreateCustomerRequest customerRequest) {
        try {
            CustomerResponse customerResponse = customerDataMapper.customerToCustomerResponse(
                    customerService.saveCustomer(customerDataMapper.customerRequestToCustomer(customerRequest)));
            return ResponseEntity.ok(customerResponse);

        } catch (CustomerAlreadyExistsException e) {
            logger.error("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }


    }

    @GetMapping
    public ResponseEntity<?> getAllCustomer() {
        List<CustomerResponse> customerResponse = customerService.getAllCustomer().stream()
                .map(customerDataMapper::customerToCustomerResponse).toList();
        if (customerResponse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No customer record");
        }
        return ResponseEntity.ok(customerResponse);

    }

    @GetMapping(value = "/email")
    public ResponseEntity<?> getCustomerByCustomerEmail(@RequestParam("email") String customerEmail) {
        try {
            CustomerResponse customerResponse = customerDataMapper
                    .customerToCustomerResponse(customerService.getCustomerByEmail(customerEmail));
            return ResponseEntity.ok(customerResponse);
        } catch (CustomerAlreadyExistsException e) {
            logger.error("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }


    }

    //PAYMENT-CARD CONTROLLERS

    @PostMapping("/payment-card")
    public ResponseEntity<?> savePaymentCardByCustomerEmail(@RequestParam("email") String email, @RequestBody PaymentCardRequest paymentCardRequest) throws Exception {

        try {
            PaymentCardResponse response = paymentCardDataMapper
                    .paymentCardToPaymentCardResponse(paymentCardService
                            .savePaymentCardByCustomerEmail(email, paymentCardDataMapper.paymentCardRequestToPaymentCard(paymentCardRequest)));
            return ResponseEntity.ok(response);

        } catch (PaymentCardAlreadyRegisteredException e) {
            logger.error("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @PostMapping("{id}/payment-card")
    public ResponseEntity<?> savePaymentCardByCustomerId(@PathVariable("id") UUID customerId, @RequestBody PaymentCardRequest paymentCardRequest) throws Exception {
        try {
            PaymentCardResponse response = paymentCardDataMapper
                    .paymentCardToPaymentCardResponse(paymentCardService.savePaymentCardByCustomerId(customerId, paymentCardDataMapper.paymentCardRequestToPaymentCard(paymentCardRequest)));
            return ResponseEntity.ok(response);
        } catch (PaymentCardAlreadyRegisteredException e) {
            logger.error("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping("/payment-card")
    public ResponseEntity<?> getPaymentCardsByCustomerEmail(@RequestParam("email") String email) {

        try {
            List<PaymentCardResponse> response = paymentCardService.getPaymentCardsByCustomerEmail(email).stream().map(paymentCardDataMapper::paymentCardToPaymentCardResponse).collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping("{id}/payment-card")
    public ResponseEntity<?> getPaymentCardsByCustomerId(@PathVariable("id") UUID id) {

        try {
            List<PaymentCardResponse> response = paymentCardService.getPaymentCardsByCustomerId(id).stream().map(paymentCardDataMapper::paymentCardToPaymentCardResponse).collect(Collectors.toList());
            return ResponseEntity.of(Optional.of(response));
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @DeleteMapping("{customerId}/payment-card/{cardId}")
    public ResponseEntity<Void> deletePaymentCardByPaymentCardId(@PathVariable("cardId") UUID paymentCardId, @PathVariable("customerId") UUID customerId) {
        paymentCardService.deletePaymentCardByPaymentCardId(paymentCardId, customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
