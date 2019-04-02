package com.ratecalculator.client;

import com.ratecalculator.client.exception.RateCalculatorArgumentException;
import com.ratecalculator.client.utils.ClientUtils;
import com.ratecalculator.core.LoanQuote;
import com.ratecalculator.core.LoanQuoteCalculator;
import com.ratecalculator.core.exception.InsufficientFundException;
import com.ratecalculator.marketdata.exception.MarketDataFileCorruptedException;
import com.ratecalculator.marketdata.exception.MarketDataReaderException;
import org.apache.log4j.Logger;


import java.math.BigDecimal;

public class RateCalculatorClient {

    private static final Logger log = Logger.getLogger(RateCalculatorClient.class);

    public static void main(String[] args) {

        log.info(ClientUtils.getCurrentTimeStamp() + "Starting Application...");
        boolean exceptionHappened = false;
        LoanQuote loanQuote;
        LoanQuoteCalculator loanQuoteCalculator = new LoanQuoteCalculator();
        try {
            ClientUtils.checkArguments(args);
        } catch ( RateCalculatorArgumentException r){
            log.error("Incorrect parameters proviced to application:");
            log.error(r.getMessage(),r.getCause());
            ClientUtils.printQuoteNotFoundMessage(null,true);
            exceptionHappened = true;
        }

        if (!exceptionHappened) {
            BigDecimal requestAmount = BigDecimal.valueOf(Double.parseDouble(args[1]));
            String csv = args[0];

            try {
                loanQuote = loanQuoteCalculator.calculate(requestAmount, csv);
                ClientUtils.printMessage(loanQuote);
            }catch (MarketDataReaderException  m) {
                log.error("MarketData File not found...");
                log.error(m.getMessage(),m.getCause());
                ClientUtils.printQuoteNotFoundMessage(requestAmount.toString(), true);
            }catch (MarketDataFileCorruptedException m){
                log.error("Corrupted Market Data File...");
                log.error(m.getMessage(),m.getCause());
                ClientUtils.printQuoteNotFoundMessage(requestAmount.toString(), true);
            }catch (InsufficientFundException e) {
                log.error("Insufficient funds in the Market....");
                log.error(e.getMessage(),e.getCause());
                ClientUtils.printQuoteNotFoundMessage(requestAmount.toString(), false);
            }
        }

    }
}
