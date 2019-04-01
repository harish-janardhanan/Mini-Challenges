package com.ratecalculator.core;

import com.ratecalculator.marketdata.exception.MarketDataReaderException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class LoanQuoteCalculatorTest {

    private static final String CSV = "src/test/resources/MarketDataforExercise.csv";

    @Test
    public void testCalculator(){
        LoanQuoteCalculator loanQuoteCalculator = new LoanQuoteCalculator();

        BigDecimal amount = BigDecimal.valueOf(1000);

        LoanQuote loanQuote = loanQuoteCalculator.calculate(amount,CSV);

        Assert.assertEquals(Double.valueOf(7.0),loanQuote.getRateOfInterest());
        Assert.assertEquals(BigDecimal.valueOf(1232.92),loanQuote.getFinalPayment());
        Assert.assertEquals(BigDecimal.valueOf(34.24),loanQuote.getMonthlyPayment());
        Assert.assertEquals(BigDecimal.valueOf(1000),loanQuote.getLoanAmount());

    }

    @Test(expected = MarketDataReaderException.class)
    public void testExceptionWhenNoFile(){
        LoanQuoteCalculator loanQuoteCalculator = new LoanQuoteCalculator();

        BigDecimal amount = BigDecimal.valueOf(1000);

        loanQuoteCalculator.calculate(amount,"WrongPath");

    }
}
