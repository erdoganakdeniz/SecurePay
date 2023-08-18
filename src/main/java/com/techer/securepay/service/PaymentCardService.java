package com.techer.securepay.service;

import com.techer.securepay.entity.PaymentCard;

import java.util.List;
import java.util.UUID;

public interface PaymentCardService {

    PaymentCard savePaymentCardByCustomerEmail(String customerEmail, PaymentCard paymentCard) throws Exception;

    PaymentCard getPaymentCardById(UUID id);

    List<PaymentCard> getPaymentCardsByCustomerEmail(String email);

    List<PaymentCard> getPaymentCardsByCustomerId(UUID customerId);


    PaymentCard savePaymentCardByCustomerId(UUID customerId, PaymentCard paymentCard) throws Exception;

    void deletePaymentCardByPaymentCardId(UUID paymentCardId, UUID customerId);

    boolean existsByCardNo(String cardNo);
}
