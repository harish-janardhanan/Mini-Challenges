package com.ratecalculator.client.exception;

public class NoArgumentsFoundException extends IllegalArgumentException {

    public NoArgumentsFoundException(String message, Throwable cause){
        super(message,cause);
    }
}
