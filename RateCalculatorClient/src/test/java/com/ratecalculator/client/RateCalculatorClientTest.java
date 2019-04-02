package com.ratecalculator.client;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class RateCalculatorClientTest {
    private static final String CSV = "src/test/resources/MarketDataforExercise.csv";
    private static final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private static void testMainForNoQuote(String[] args, ByteArrayOutputStream outContent) {
        RateCalculatorClient.main(args);
        Assert.assertTrue(outContent.toString().contains(" is not available at the moment"));
        Assert.assertTrue(outContent.toString().contains("Tip: Have checked your input parameters?"));
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testLoanAmountCalculator() {

        String[] args = {CSV, "1000"};
        RateCalculatorClient.main(args);

        Assert.assertTrue(outContent.toString().contains("Requested Amount:1,000"));
        Assert.assertTrue(outContent.toString().contains("Rate:7"));
        Assert.assertTrue(outContent.toString().contains("Monthly Repayment:34.24"));
        Assert.assertTrue(outContent.toString().contains("Total Repayment:1,232.92"));

    }

    @Test
    public void testFlippedParameters() {
        String[] args = {"1000", CSV};
        testMainForNoQuote(args, outContent);
    }

    @Test
    public void testAmountLessthan1000() {
        String[] args = {CSV, "100"};
        testMainForNoQuote(args, outContent);
    }

    @Test
    public void testAmoountGreaterThan15000() {
        String[] args = {CSV, "100000000000000"};
        testMainForNoQuote(args, outContent);
    }

    @Test
    public void testInsufficientFunds() {
        String[] args = {CSV, "3500"};
        RateCalculatorClient.main(args);
        Assert.assertTrue(outContent.toString().contains(" is not available at the moment"));
        Assert.assertFalse(outContent.toString().contains("Tip: Have checked your input parameters?"));
    }

    @Test
    public void numberFormatException() {
        String[] args = {CSV, "IAmAString"};
        testMainForNoQuote(args, outContent);
    }

}
