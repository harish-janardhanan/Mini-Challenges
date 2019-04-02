package com.ratecalculator.client.utils;

import com.ratecalculator.client.exception.InCorrectArgumentsFoundException;
import com.ratecalculator.client.exception.RateCalculatorArgumentException;
import com.ratecalculator.core.LoanQuote;
import com.ratecalculator.core.LoanQuoteCalculator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

public class ClientUtilsTest {

    private static final String CSV = "src/test/resources/MarketDataforExercise.csv";
    private static final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test(expected = InCorrectArgumentsFoundException.class)
    public void testNoArguments() {
        String[] args = {};
        ClientUtils.checkArguments(args);
    }

    @Test(expected = RateCalculatorArgumentException.class)
    public void testAmountMoreThan15000() {
        String[] args = {"25000", "csv"};
        ClientUtils.checkArguments(args);
    }

    @Test(expected = RateCalculatorArgumentException.class)
    public void testAmountLessThan1000() {
        String[] args = {"300", "csv"};
        ClientUtils.checkArguments(args);
    }

    @Test(expected = RateCalculatorArgumentException.class)
    public void testAmountNotIncrementOf100() {
        String[] args = {"1350", "csv"};
        ClientUtils.checkArguments(args);
    }

    @Test
    public void testQuoteNotFound() {
        ClientUtils.printQuoteNotFoundMessage("100", true);
        Assert.assertTrue(outContent.toString().contains("Your quote for 100 is not available at the moment"));
        Assert.assertTrue(outContent.toString().contains("Tip: Have checked your input parameters?"));
    }

    @Test
    public void testQuoteFound() {
        LoanQuoteCalculator loanQuoteCalculator = new LoanQuoteCalculator();

        BigDecimal amount = BigDecimal.valueOf(1000);

        LoanQuote loanQuote = loanQuoteCalculator.calculate(amount, CSV);
        ClientUtils.printMessage(loanQuote);

        Assert.assertTrue(outContent.toString().contains("Requested Amount:£1000.00"));
        Assert.assertTrue(outContent.toString().contains("Rate:7.0%"));
        Assert.assertTrue(outContent.toString().contains("Monthly Repayment:£34.24"));
        Assert.assertTrue(outContent.toString().contains("Total Repayment:£1232.92"));
    }

}
