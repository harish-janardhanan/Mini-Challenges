package com.ratecalculator.client.exception;

public class InCorrectArgumentsFoundException extends IllegalArgumentException {

    public InCorrectArgumentsFoundException(String message, Throwable cause){
        super(message,cause);
    }
}
