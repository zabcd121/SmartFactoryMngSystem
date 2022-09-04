package com.mirae.smartfactory.exception;

public class InvalidPWException extends RuntimeException{
    public InvalidPWException() {
        super();
    }

    public InvalidPWException(String message) {
        super(message);
    }

    public InvalidPWException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPWException(Throwable cause) {
        super(cause);
    }

    protected InvalidPWException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
