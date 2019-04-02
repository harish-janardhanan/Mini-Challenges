package com.ratecalculator.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public final class LoanQuoteCalculator {

    private static final Logger log = LoggerFactory.getLogger(LoanQuoteCalculator.class);
    public LoanQuoteCalculator() {
        //Default Constructor
    }

    public LoanQuote calculate(BigDecimal amount, String csv) {
        return calculateLoanQuote(amount, csv);
    }

    private LoanQuote calculateLoanQuote(BigDecimal amount, String csv) {
        log.debug("Calculating Loan Quote for amount = {}, with market data file {}", amount,csv);
        CostOfFundCalculator costOfFunds = new CostOfFundCalculator(csv);
        Double rate = costOfFunds.calculate(amount);
        BigDecimal finalAmount = new AmountCalculator().calculateAmount(amount, rate * .01);

        log.debug("Final rate = {}, amount = {}", rate,amount);
        LoanQuote loanQuote = new LoanQuote(amount, finalAmount.divide(new BigDecimal(36), BigDecimal.ROUND_DOWN), finalAmount, rate);
        log.debug("Loan quote = {}" , loanQuote);
        return loanQuote;
    }
}
