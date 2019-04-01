package com.ratecalculator.client;

import com.ratecalculator.client.exception.RateCalculatorArgumentException;
import com.ratecalculator.client.exception.RateCalculatorClientException;
import com.ratecalculator.client.utils.ClientUtils;
import com.ratecalculator.core.LoanQuote;
import com.ratecalculator.core.LoanQuoteCalculator;
import com.ratecalculator.core.exception.RateCalculatorException;
import com.ratecalculator.marketdata.exception.MarketDataReaderException;


import java.math.BigDecimal;

public class RateCalculatorClient {

    public static void main(String[] args) {
        LoanQuote loanQuote;
        LoanQuoteCalculator loanQuoteCalculator = new LoanQuoteCalculator();
        try {
            ClientUtils.checkArguments(args);
        } catch (RateCalculatorClientException | RateCalculatorArgumentException r){
            ClientUtils.printQuoteNotFoundMessage(null,true);
            System.exit(0);
        }

        BigDecimal requestAmount = BigDecimal.valueOf(Double.parseDouble(args[1]));
        String csv = args[0];

        try {
            loanQuote = loanQuoteCalculator.calculate(requestAmount,csv);
            ClientUtils.printMessage(loanQuote);
        }catch (MarketDataReaderException | RateCalculatorArgumentException  m) {
            ClientUtils.printQuoteNotFoundMessage(requestAmount.toString(),true);
            System.exit(0);
        }catch (RateCalculatorException e){
            ClientUtils.printQuoteNotFoundMessage(requestAmount.toString(),false);
            System.exit(0);
        }


    }
}
