package com.dev.spring.exceptions;

public class DataProcessingException extends RuntimeException {

    public DataProcessingException(String message) {
        super(message);
    }

    public DataProcessingException(String message, Throwable text) {
        super(message, text);
    }
}
