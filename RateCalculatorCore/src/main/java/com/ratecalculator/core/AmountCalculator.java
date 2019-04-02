package com.ratecalculator.core;

import com.ratecalculator.core.exception.RateCalculatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

final class AmountCalculator implements ICalculator {

    private static final int TERM = 36;
    private static final Double COMPOUNDING_TERM = 12.0;
    private static final Logger log = LoggerFactory.getLogger(AmountCalculator.class);

    @Override
    public Double calculate(BigDecimal amount) {
        throw new RateCalculatorException("Unsupported Operation", new Throwable("Calculate method not supported for Amount Calculator"));
    }

    @Override
    public BigDecimal calculateAmount(BigDecimal amount, Double rate) {

        log.debug("Calculating rate with paramenters Amount = {}, rate = {}",amount,rate);
        double rBynPlusOne = (rate / COMPOUNDING_TERM) + 1.0;
        log.debug("1 + r/n = {}", rBynPlusOne);
        log.debug("Final Repayment = {}", amount.multiply(BigDecimal.valueOf(rBynPlusOne).pow(TERM)) );

        return amount.multiply(BigDecimal.valueOf(rBynPlusOne).pow(TERM)).setScale(2, BigDecimal.ROUND_DOWN);
    }
}
