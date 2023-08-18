package com.techer.securepay.service;

import com.techer.securepay.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PaymentService {


    Payment createPaymentByCustomerId(UUID customerId,Payment payment,boolean registerCard) throws Exception;

    Page<Payment> getAllPaymentByDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable);
    Page<Payment> getAllPaymentByDateRangeByCustomerEmail(LocalDate startDate, LocalDate endDate, Pageable pageable,String customerEmail);

    List<Payment> getAllPaymentByCustomerId(UUID customerId);
    List<Payment> getAllPaymentByPaymentCardNumber(String cardNumber) throws Exception;



}
