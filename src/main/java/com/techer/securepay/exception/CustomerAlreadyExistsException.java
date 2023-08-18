package com.techer.securepay.exception;

public class CustomerAlreadyExistsException extends RuntimeException{
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
