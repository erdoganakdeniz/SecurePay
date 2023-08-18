package com.techer.securepay.mapper;

import com.techer.securepay.dto.request.PaymentCardRequest;
import com.techer.securepay.dto.response.PaymentCardResponse;
import com.techer.securepay.entity.PaymentCard;
import com.techer.securepay.util.PaymentCardUtil;
import org.springframework.stereotype.Component;

@Component
public class PaymentCardDataMapper {


    private final PaymentCardUtil paymentCardUtil;

    public PaymentCardDataMapper(PaymentCardUtil paymentCardUtil) {

        this.paymentCardUtil = paymentCardUtil;
    }


    public PaymentCardResponse paymentCardToPaymentCardResponse(PaymentCard paymentCard) {

        try {
            return new PaymentCardResponse(paymentCard.getId(),
                    paymentCard.getCardAlias(),
                    paymentCardUtil.getBinNumber(paymentCard));
        } catch (Exception e) {
            return null;
        }


    }

    public PaymentCard paymentCardRequestToPaymentCard(PaymentCardRequest paymentCardRequest) {
        return new PaymentCard.Builder().cardAlias(paymentCardRequest.cardAlias())
                .cardNumber(paymentCardRequest.cardNumber())
                .cardHolderName(paymentCardRequest.cardHolderName())
                .expireMonth(paymentCardRequest.expireMonth())
                .expireYear(paymentCardRequest.expireYear())
                .cvc(paymentCardRequest.cvc()).build();
    }
}
