package com.ratecalculator.core;


import java.math.BigDecimal;

public interface ICalculator {

    public Double calculate(BigDecimal amount);

    public BigDecimal calculateAmount(BigDecimal amount, Double rate);
}
