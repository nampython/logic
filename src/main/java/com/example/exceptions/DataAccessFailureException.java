package com.example.exceptions;

public class DataAccessFailureException extends RuntimeException {
    public DataAccessFailureException(String message) {
        super(message);
    }

    public DataAccessFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
