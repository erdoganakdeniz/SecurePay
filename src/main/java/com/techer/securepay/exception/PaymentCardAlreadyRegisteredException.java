package com.techer.securepay.exception;

public class PaymentCardAlreadyRegisteredException extends RuntimeException{
    public PaymentCardAlreadyRegisteredException(String message) {
        super(message);
    }
}
