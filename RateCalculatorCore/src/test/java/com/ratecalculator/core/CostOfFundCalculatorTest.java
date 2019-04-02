package com.ratecalculator.core;

import com.ratecalculator.core.exception.InsufficientFundException;
import com.ratecalculator.marketdata.exception.MarketDataReaderException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class CostOfFundCalculatorTest {

    private static final String CSV = "src/test/resources/MarketDataforExercise.csv";

    @Test
    public void testCostOfFundCalculator(){
        CostOfFundCalculator costOfFundCalculator = new CostOfFundCalculator(CSV);

        BigDecimal amount = BigDecimal.valueOf(1200.00);

        Assert.assertEquals(Double.valueOf(7.05),costOfFundCalculator.calculate(amount));
    }

    @Test(expected = InsufficientFundException.class)
    public void testExceptionWhenNoFunds(){
        CostOfFundCalculator costOfFundCalculator = new CostOfFundCalculator(CSV);

        BigDecimal amount = BigDecimal.valueOf(20000.00);
        costOfFundCalculator.calculate(amount);
    }

    @Test(expected = InsufficientFundException.class)
    public void testExceptionWhenUnsupported(){
        CostOfFundCalculator costOfFundCalculator = new CostOfFundCalculator(CSV);

        costOfFundCalculator.calculateAmount(new BigDecimal(0), 0.0);

    }

    @Test(expected = MarketDataReaderException.class)
    public void testExceptionWhenNoFile(){
        CostOfFundCalculator costOfFundCalculator = new CostOfFundCalculator("wrongfile");
        costOfFundCalculator.calculate(BigDecimal.valueOf(200));
    }
}
