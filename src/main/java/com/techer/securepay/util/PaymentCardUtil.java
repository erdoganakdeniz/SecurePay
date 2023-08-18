package com.techer.securepay.util;

import com.techer.securepay.entity.PaymentCard;
import org.springframework.stereotype.Component;

@Component
public class PaymentCardUtil {


    public String getBinNumber(PaymentCard paymentCard) throws Exception {
        String cardNumber = decryptCardNumber(paymentCard);
        return cardNumber.substring(cardNumber.length() - 6);
    }

    public String encryptCardNumber(PaymentCard paymentCard) throws Exception {
        return EncryptionUtil.encrypt(paymentCard.getCardNumber());
    }

    public String decryptCardNumber(PaymentCard paymentCard) throws Exception {
        return EncryptionUtil.decrypt(paymentCard.getCardNumber());

    }
}
