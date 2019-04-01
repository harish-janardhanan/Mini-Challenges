package com.ratecalculator.core;

import com.ratecalculator.core.exception.RateCalculatorException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class AmountCalculatorTest {

    @Test
    public void testCalculateAmount(){
        AmountCalculator amountCalculator = new AmountCalculator();

        BigDecimal amount = BigDecimal.valueOf(1000.00);
        Double rate =0.065;
        //Expected result calculated by providing parameters
        // to https://www.thecalculatorsite.com/articles/finance/compound-interest-formula.php
        Assert.assertEquals(BigDecimal.valueOf(1214.67),amountCalculator.calculateAmount(amount,rate));
    }

    @Test(expected = RateCalculatorException.class)
    public void testExceptionThrownWhenUnsupportedOperation(){
        AmountCalculator amountCalculator = new AmountCalculator();

        BigDecimal amount = BigDecimal.valueOf(1000.00);

        amountCalculator.calculate(amount);
    }
}
