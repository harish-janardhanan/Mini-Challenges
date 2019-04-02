package com.ratecalculator.client.exception;

public class FundOutOfRangeException extends RuntimeException {
    public FundOutOfRangeException(String message, Throwable cause) {
        super(message, cause);
    }
}
