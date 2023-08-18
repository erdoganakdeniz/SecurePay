package com.techer.securepay.service;

import com.techer.securepay.entity.Customer;
import com.techer.securepay.entity.Payment;
import com.techer.securepay.exception.InvalidAmountException;
import com.techer.securepay.repository.PaymentRepository;
import com.techer.securepay.util.PaymentCardUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final CustomerService customerService;
    private final PaymentCardService paymentCardService;

    private final PaymentCardUtil paymentCardUtil;


    public PaymentServiceImpl(PaymentRepository paymentRepository, CustomerService customerService, PaymentCardService paymentCardService, PaymentCardUtil paymentCardUtil) {
        this.paymentRepository = paymentRepository;
        this.customerService = customerService;
        this.paymentCardService = paymentCardService;

        this.paymentCardUtil = paymentCardUtil;
    }

    @Override
    public Payment createPaymentByCustomerId(UUID customerId, Payment payment, boolean registerCard) throws Exception {

        Customer customer = customerService.getCustomerByCustomerId(customerId);

        if (!payment.isAmountValid(payment.getAmount())) {
            throw new InvalidAmountException("Amount must be greater than 0");

        }
        if (registerCard) {
            paymentCardService.savePaymentCardByCustomerId(customerId, payment.getPaymentCard());
        }
        payment.getPaymentCard().setCardNumber(paymentCardUtil.encryptCardNumber(payment.getPaymentCard()));
        payment.setPaymentCard(payment.getPaymentCard());
        customer.getPaymentList().add(payment);
        customerService.updateCustomerByCustomerId(customerId, customer);
        payment.setCustomer(customer);
        return paymentRepository.save(payment);
    }


    @Override
    public Page<Payment> getAllPaymentByDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return paymentRepository.findPaymentByCreatedAtBetween(startDate, endDate, pageable);
    }

    @Override
    public Page<Payment> getAllPaymentByDateRangeByCustomerEmail(LocalDate startDate, LocalDate endDate, Pageable pageable, String customerEmail) {
        return paymentRepository.findPaymentByCreatedAtBetweenAndCustomer_Email(startDate, endDate, pageable, customerEmail);
    }

    @Override
    public List<Payment> getAllPaymentByCustomerId(UUID customerId) {
        return paymentRepository.findAllByCustomer_Id(customerId);
    }

    @Override
    public List<Payment> getAllPaymentByPaymentCardNumber(String cardNumber) throws Exception {
        return paymentRepository.findAllByPaymentCard_CardNumber(cardNumber);
    }
}
