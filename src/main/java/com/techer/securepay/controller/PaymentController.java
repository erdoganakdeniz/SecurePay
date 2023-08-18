package com.techer.securepay.controller;


import com.techer.securepay.dto.request.CreatePaymentRequest;
import com.techer.securepay.entity.Payment;
import com.techer.securepay.mapper.PaymentDataMapper;
import com.techer.securepay.service.PaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/payment")
@RestController
@Tag(name = "Payment API")
public class PaymentController {

    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final PaymentService paymentService;
    private final PaymentDataMapper paymentDataMapper;

    public PaymentController(PaymentService paymentService, PaymentDataMapper paymentDataMapper) {
        this.paymentService = paymentService;
        this.paymentDataMapper = paymentDataMapper;
    }


    @GetMapping
    public ResponseEntity<Page<Payment>> getAllPayment(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                       @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Payment> payments = paymentService.getAllPaymentByDateRange(startDate, endDate, pageable);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Page<Payment>> getAllPaymentByCustomerEmail(@PathVariable("email") String email, @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                      @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                                      @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Payment> payments = paymentService.getAllPaymentByDateRangeByCustomerEmail(startDate, endDate, pageable, email);
        return ResponseEntity.ok(payments);
    }

    @PostMapping
    public ResponseEntity<?> createPayment(@RequestParam UUID customerId, @RequestBody CreatePaymentRequest paymentRequest, boolean registerCard) throws Exception {
        try {
            Payment response = paymentService.createPaymentByCustomerId(customerId, paymentDataMapper.createPaymentRequestToPayment(paymentRequest), registerCard);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }

    }

    @GetMapping("/cardNo")
    public ResponseEntity<?> getAllPaymentByCardNumber(@RequestParam String cardNumber) {
        try {
            List<Payment> response = paymentService.getAllPaymentByPaymentCardNumber(cardNumber);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/customer")
    public ResponseEntity<?> getAllPaymentByCustomerId(@RequestParam UUID customerId) {
        try {
            List<Payment> response = paymentService.getAllPaymentByCustomerId(customerId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
