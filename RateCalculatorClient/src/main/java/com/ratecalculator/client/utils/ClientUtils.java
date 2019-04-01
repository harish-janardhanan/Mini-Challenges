package com.ratecalculator.client.utils;

import com.ratecalculator.client.exception.RateCalculatorArgumentException;
import com.ratecalculator.core.LoanQuote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;


public class ClientUtils {

    private ClientUtils(){}
    private static final Logger log = LoggerFactory.getLogger(ClientUtils.class);

    public static void checkArguments(String[] args){
        if (args.length == 0 || args.length > 2){
            log.error("Incorrect Number of Arguments passed in the program {}", args.length);
            throw  new RateCalculatorArgumentException("Incorrect Number of Arguments");
        }

        try {
            Double.parseDouble(args[1]);
        }catch (NumberFormatException n){
            log.error("Incorrect amount specified {}", args[1]);
            log.error(n.getMessage());
            throw new RateCalculatorArgumentException("Incorrect Amount Specified");
        }catch (ArrayIndexOutOfBoundsException a){
            log.error("No argument specified {}", args[1]);
            log.error(a.getMessage());
            throw new RateCalculatorArgumentException("Incorrect Amount Specified");
        }

    }

    public static void printQuoteNotFoundMessage(String ask, boolean exception){
        System.out.println(MessageFormat.format("Your quote for {0} is not available at the moment, please try later...", ask));
        if (exception) {
            System.out.println("Tip: Have checked your input parameters?  ");
            System.out.println("Usage is : java -jar RateCalculatorClient.jar [csvfile] [amount]");
            usage();
        }
    }

    public static void printMessage(LoanQuote loanQuote) {
        System.out.println(MessageFormat.format("Requested Amount:{0}",loanQuote.getLoanAmount()));
        System.out.println(MessageFormat.format("Rate:{0}",loanQuote.getRateOfInterest()));
        System.out.println(MessageFormat.format("Monthly Repayment:{0}",loanQuote.getMonthlyPayment()));
        System.out.println(MessageFormat.format("Total Repayment:{0}",loanQuote.getFinalPayment()));
    }

    public static void usage(){
        System.out.println("[csvfile] ----------------------------- path to market data file");
        System.out.println("[amount]  -----------------------------     Required Loan amount");
    }
}