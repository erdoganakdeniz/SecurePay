package com.techer.securepay.service;

import com.techer.securepay.entity.Customer;
import com.techer.securepay.entity.PaymentCard;
import com.techer.securepay.exception.PaymentCardAlreadyRegisteredException;
import com.techer.securepay.exception.PaymentCardNotFoundException;
import com.techer.securepay.repository.PaymentCardRepository;
import com.techer.securepay.util.EncryptionUtil;
import com.techer.securepay.util.PaymentCardUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentCardServiceImpl implements PaymentCardService {

    private final PaymentCardRepository paymentCardRepository;
    private final CustomerService customerService;

    private final PaymentCardUtil paymentCardUtil;


    public PaymentCardServiceImpl(PaymentCardRepository paymentCardRepository, CustomerService customerService, PaymentCardUtil paymentCardUtil) {
        this.paymentCardRepository = paymentCardRepository;
        this.customerService = customerService;
        this.paymentCardUtil = paymentCardUtil;
    }

    @Override
    public PaymentCard savePaymentCardByCustomerEmail(String customerEmail, PaymentCard paymentCard) throws Exception {
        Customer customer = customerService.getCustomerByEmail(customerEmail);
        paymentCard.setCardNumber(paymentCardUtil.encryptCardNumber(paymentCard));
        if (paymentCardRepository.existsByCardNumber(paymentCard.getCardNumber())) {
            throw new PaymentCardAlreadyRegisteredException("Payment card already registered!!");
        }

        customer.getSavedCards().add(paymentCard);
        customerService.updateCustomerByCustomerEmail(customerEmail, customer);
        return paymentCardRepository.save(paymentCard);
    }

    @Override
    public PaymentCard getPaymentCardById(UUID id) {
        return paymentCardRepository.findById(id)
                .orElseThrow(() -> new PaymentCardNotFoundException("Payment card not found with given id: " + id));
    }

    @Override
    public List<PaymentCard> getPaymentCardsByCustomerEmail(String email) {
        Customer customer = customerService.getCustomerByEmail(email);
        customer.getSavedCards().forEach(paymentCard -> {
            try {
                paymentCard.setCardNumber(paymentCardUtil.decryptCardNumber(paymentCard));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return customer.getSavedCards();
    }

    @Override
    public List<PaymentCard> getPaymentCardsByCustomerId(UUID customerId) {
        Customer customer = customerService.getCustomerByCustomerId(customerId);
        return customer.getSavedCards();
    }

    @Override
    public PaymentCard savePaymentCardByCustomerId(UUID customerId, PaymentCard paymentCard) throws Exception {
        Customer customer = customerService.getCustomerByCustomerId(customerId);
        paymentCard.setCardNumber(EncryptionUtil.encrypt(paymentCard.getCardNumber()));
        if (existsByCardNo(paymentCard.getCardNumber())) {
            throw new PaymentCardAlreadyRegisteredException("Payment card already registered!!");
        }
        customer.getSavedCards().add(paymentCard);
        customerService.updateCustomerByCustomerId(customerId, customer);
        return paymentCardRepository.save(paymentCard);
    }

    @Override
    public void deletePaymentCardByPaymentCardId(UUID paymentCardId, UUID customerId) {

        if (paymentCardRepository.existsById(paymentCardId)) {
            PaymentCard paymentCard = getPaymentCardById(paymentCardId);
            Customer customer = customerService.getCustomerByCustomerId(customerId);
            customer.getSavedCards().remove(paymentCard);
            customerService.updateCustomerByCustomerId(customerId, customer);
            paymentCardRepository.deleteById(paymentCardId);
        }

    }

    @Override
    public boolean existsByCardNo(String cardNo) {
        return paymentCardRepository.existsByCardNumber(cardNo);
    }


}
