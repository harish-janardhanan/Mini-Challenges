package com.ratecalculator.core;

import com.ratecalculator.core.exception.InsufficientFundException;

import java.math.BigDecimal;

final class AmountCalculator implements ICalculator {

    private static final int TERM = 36;
    private static final Double COMPOUNDING_TERM = 12.0;
    @Override
    public Double calculate(BigDecimal amount) {
        throw new InsufficientFundException("Unsupported Operation");
    }

    @Override
    public BigDecimal calculateAmount(BigDecimal amount, Double rate) {
        Double rBynPlusOne = (rate / COMPOUNDING_TERM) + 1.0;

        return amount.multiply(BigDecimal.valueOf(rBynPlusOne).pow(TERM)).setScale(2,BigDecimal.ROUND_DOWN);
    }
}
