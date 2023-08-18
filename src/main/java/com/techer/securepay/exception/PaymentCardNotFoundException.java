package com.techer.securepay.exception;

public class PaymentCardNotFoundException extends RuntimeException{
    public PaymentCardNotFoundException(String message) {
        super(message);
    }
}
