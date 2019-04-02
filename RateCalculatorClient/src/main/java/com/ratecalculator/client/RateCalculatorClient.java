package com.ratecalculator.client;

import com.ratecalculator.client.exception.FundOutOfRangeException;
import com.ratecalculator.client.exception.InCorrectArgumentsFoundException;
import com.ratecalculator.client.exception.RateCalculatorArgumentException;
import com.ratecalculator.client.utils.ClientUtils;
import com.ratecalculator.core.LoanQuote;
import com.ratecalculator.core.LoanQuoteCalculator;
import com.ratecalculator.core.exception.InsufficientFundException;
import com.ratecalculator.core.exception.RateCalculatorException;
import com.ratecalculator.marketdata.exception.MarketDataFileCorruptedException;
import com.ratecalculator.marketdata.exception.MarketDataReaderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class RateCalculatorClient {

    private static final Logger log = LoggerFactory.getLogger(RateCalculatorClient.class);

    public static void main(String[] args) {
        log.info("Starting Application ...");
        boolean exceptionHappened = false;
        LoanQuote loanQuote;
        LoanQuoteCalculator loanQuoteCalculator = new LoanQuoteCalculator();
        try {
            ClientUtils.checkArguments(args);
        } catch (RateCalculatorArgumentException r) {
            log.error("Incorrect parameters provided to application {} ", args.length);
            log.error(r.getMessage(), r.getCause());
            ClientUtils.printQuoteNotFoundMessage(args[1], true);
            exceptionHappened = true;
        } catch (FundOutOfRangeException f) {
            log.error("The current ask for {} is invalid...", args[1]);
            log.error(f.getMessage(), f.getCause());
            ClientUtils.printQuoteNotFoundMessage(args[1], false);
            exceptionHappened = true;
        } catch (InCorrectArgumentsFoundException n) {
            log.error("No Arguments passed - No Of Arguments is : {} " , args.length);
            log.error(n.getMessage(), n.getCause());
            ClientUtils.printQuoteNotFoundMessage("NA", true);
            exceptionHappened = true;
        }

        if (!exceptionHappened) {
            BigDecimal requestAmount = BigDecimal.valueOf(Double.parseDouble(args[1]));
            String csv = args[0];

            log.debug("Amount = {}, CSV File = {}",requestAmount, csv);

            try {
                loanQuote = loanQuoteCalculator.calculate(requestAmount, csv);
                ClientUtils.printMessage(loanQuote);
            } catch (MarketDataReaderException m) {
                log.error("MarketData File not found...");
                log.error(m.getMessage(), m.getCause());
                ClientUtils.printQuoteNotFoundMessage(requestAmount.toString(), true);
            } catch (MarketDataFileCorruptedException m) {
                log.error("Corrupted Market Data File...");
                log.error(m.getMessage(), m.getCause());
                ClientUtils.printQuoteNotFoundMessage(requestAmount.toString(), true);
            } catch (InsufficientFundException e) {
                log.error("Insufficient funds in the Market....");
                log.error(e.getMessage(), e.getCause());
                ClientUtils.printQuoteNotFoundMessage(requestAmount.toString(), false);
            } catch (RateCalculatorException r) {
                log.error("Unsupported operation");
                log.error(r.getMessage(), r.getCause());
                ClientUtils.printQuoteNotFoundMessage(requestAmount.toString(), true);
            }
        }

        log.info("Execution Complete...");

    }
}
