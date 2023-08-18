package com.techer.securepay.exception;

public class InvalidAmountException extends RuntimeException{
    public InvalidAmountException(String message) {
        super(message);
    }
}
