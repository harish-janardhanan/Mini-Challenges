package com.ratecalculator.core;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class LoanQuoteTest {

    @Test
    public void testToString(){
        LoanQuote loanQuote = new LoanQuote(BigDecimal.valueOf(100),
                BigDecimal.valueOf(20),
                BigDecimal.valueOf(120),
                1.5);

        Assert.assertEquals("LoanQuote{" +
                        "loanAmount=" + BigDecimal.valueOf(100) +
                        ", monthlyPayment=" + BigDecimal.valueOf(20) +
                        ", finalPayment=" + BigDecimal.valueOf(120) +
                        ", rateOfInterest=" + 1.5 +
                        '}', loanQuote.toString()
                );
    }
}
