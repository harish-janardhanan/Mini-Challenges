package com.ratecalculator.marketdata.exception;


public class MarketDataFileCorruptedException extends RuntimeException {
    public MarketDataFileCorruptedException(String message, Throwable cause) {
        super(message, cause);
    }
}
