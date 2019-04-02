package com.ratecalculator.core;

import java.math.BigDecimal;

public final class LoanQuoteCalculator {

    public LoanQuoteCalculator() {
        //Default Constructor
    }

    public LoanQuote calculate(BigDecimal amount, String csv) {
        return calculateLoanQuote(amount, csv);
    }

    private LoanQuote calculateLoanQuote(BigDecimal amount, String csv) {
        CostOfFundCalculator costOfFunds = new CostOfFundCalculator(csv);
        Double rate = costOfFunds.calculate(amount);
        BigDecimal finalAmount = new AmountCalculator().calculateAmount(amount, rate * .01);

        return new LoanQuote(amount, finalAmount.divide(new BigDecimal(36), BigDecimal.ROUND_DOWN), finalAmount, rate);
    }
}
