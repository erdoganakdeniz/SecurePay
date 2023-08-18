package com.techer.securepay.mapper;

import com.techer.securepay.dto.request.CreatePaymentRequest;
import com.techer.securepay.entity.Payment;
import com.techer.securepay.service.CustomerService;
import com.techer.securepay.service.PaymentCardService;
import org.springframework.stereotype.Component;

@Component
public class PaymentDataMapper {


    private final PaymentCardDataMapper paymentCardDataMapper;

    public PaymentDataMapper(PaymentCardDataMapper paymentCardDataMapper) {

        this.paymentCardDataMapper = paymentCardDataMapper;
    }

    public Payment createPaymentRequestToPayment(CreatePaymentRequest paymentRequest) {

        return new Payment.Builder()
                .paymentCard(paymentCardDataMapper.paymentCardRequestToPaymentCard(paymentRequest.paymentCard()))
                .amount(paymentRequest.amount())
                .currency(paymentRequest.currency())
                .createdAt(paymentRequest.createdAt())
                .build();

    }
}
