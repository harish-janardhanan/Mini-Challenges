package com.ratecalculator.client.utils;

import com.ratecalculator.client.exception.FundOutOfRangeException;
import com.ratecalculator.client.exception.InCorrectArgumentsFoundException;
import com.ratecalculator.client.exception.RateCalculatorArgumentException;
import com.ratecalculator.core.LoanQuote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.MessageFormat;


public final class ClientUtils {

    private static final Logger log = LoggerFactory.getLogger(ClientUtils.class);

    private ClientUtils() {
    }

    public static void checkArguments(String[] args) {
        log.debug("Arguments checker with values {}" ,args);
        if (args.length == 0 || args.length > 2) {
            log.error("Incorrect Number of Arguments passed in the program {}", args.length);
            throw new InCorrectArgumentsFoundException("Incorrect Number of Arguments", new Throwable("Incorrect Arguments entered"));
        }

        try {
            BigDecimal requestAmount = BigDecimal.valueOf(Double.parseDouble(args[1]));
            if (requestAmount.compareTo(BigDecimal.valueOf(1000)) < 0 ||
                    requestAmount.compareTo(BigDecimal.valueOf(15000)) > 0 ||
                    requestAmount.doubleValue() % 100 != 0) {
                log.error("Requested amount out of range: {}", requestAmount);
                throw new FundOutOfRangeException("Requested amount out of range",
                        new Throwable("Amount Not Available for quote"));
            }

        } catch (NumberFormatException n) {
            log.error("Incorrect amount specified {}", args[1]);
            log.error(n.getMessage(), n.getCause());
            throw new RateCalculatorArgumentException("Incorrect Amount Specified");
        } catch (ArrayIndexOutOfBoundsException a) {
            log.error("No argument specified {}", args[1]);
            log.error(a.getMessage(), a.getCause());
            throw new RateCalculatorArgumentException("Incorrect Amount Specified");
        }

    }

    public static void printQuoteNotFoundMessage(String ask, boolean exception) {
        System.out.println(MessageFormat.format("Your quote for {0} is not available at the moment, please try later...", ask));
        if (exception) {
            System.out.println("Tip: Have checked your input parameters?  ");
            System.out.println("Usage is : java -jar RateCalculatorClient.jar [csvfile] [amount]");
            usage();
        }
    }

    public static void printMessage(LoanQuote loanQuote) {
        System.out.println(String.format("Requested Amount:£%.2f",
                loanQuote.getLoanAmount()));
        System.out.println(String.format("Rate:%2.1f%%",
                BigDecimal.valueOf(loanQuote.getRateOfInterest()).setScale(1, BigDecimal.ROUND_DOWN)));
        System.out.println(String.format("Monthly Repayment:£%.2f",
                loanQuote.getMonthlyPayment().setScale(2, BigDecimal.ROUND_DOWN)));
        System.out.println(String.format("Total Repayment:£%.2f",
                loanQuote.getFinalPayment().setScale(2, BigDecimal.ROUND_DOWN)));
    }


    public static void usage() {
        System.out.println("[csvfile] ----------------------------- path to market data file");
        System.out.println("[amount]  -----------------------------     Required Loan amount");
    }
}
