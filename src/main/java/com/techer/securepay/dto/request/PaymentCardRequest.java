package com.techer.securepay.dto.request;

public record PaymentCardRequest(String cardAlias,String cardHolderName,String cardNumber,String expireYear,String expireMonth,String cvc) {
}
