package com.ratecalculator.core;

import java.math.BigDecimal;

public final class LoanQuote {
    private BigDecimal loanAmount;
    private BigDecimal monthlyPayment;
    private BigDecimal finalPayment;
    private Double rateOfInterest;

    LoanQuote(BigDecimal loanAmount, BigDecimal monthlyPayment, BigDecimal finalPayment, Double rateOfInterest) {
        this.loanAmount = loanAmount;
        this.monthlyPayment = monthlyPayment;
        this.finalPayment = finalPayment;
        this.rateOfInterest = rateOfInterest;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public BigDecimal getFinalPayment() {
        return finalPayment;
    }

    public Double getRateOfInterest() {
        return rateOfInterest;
    }

    @Override
    public String toString() {
        return "LoanQuote{" +
                "loanAmount=" + loanAmount +
                ", monthlyPayment=" + monthlyPayment +
                ", finalPayment=" + finalPayment +
                ", rateOfInterest=" + rateOfInterest +
                '}';
    }
}
