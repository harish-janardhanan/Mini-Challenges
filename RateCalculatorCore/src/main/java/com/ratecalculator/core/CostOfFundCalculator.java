package com.ratecalculator.core;

import com.ratecalculator.core.exception.InsufficientFundException;
import com.ratecalculator.core.exception.RateCalculatorException;
import com.ratecalculator.marketdata.MarketData;
import com.ratecalculator.marketdata.MarketDataCsvAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

final class CostOfFundCalculator implements ICalculator {

    private final MarketDataCsvAO marketDataCsvAO;
    private static final Logger log = LoggerFactory.getLogger(CostOfFundCalculator.class);

    CostOfFundCalculator(String csvPath) {
        this.marketDataCsvAO = new MarketDataCsvAO(csvPath);
    }

    @Override
    public Double calculate(BigDecimal amount) {
        log.debug("Calculating rate for amount {}......", amount);
        Map<BigDecimal, Double> rateCard = new HashMap<>();
        List<BigDecimal> loanAmountList = new ArrayList<>();
        List<MarketData> marketData = marketDataCsvAO.getAll();
        BigDecimal totalLoan = amount;
        Collections.sort(marketData);
        log.debug("Sorted Collection of rates : {}", marketData );

        for (MarketData mktData : marketData) {
            if (amount.compareTo(mktData.getAvailableFunds()) <= 0) {
                rateCard.put(amount, mktData.getLendingRate());
                loanAmountList.add(amount);
                amount = amount.subtract(amount);
                break;
            } else {
                rateCard.put(mktData.getAvailableFunds(), mktData.getLendingRate());
                loanAmountList.add(mktData.getAvailableFunds());
                amount = amount.subtract(mktData.getAvailableFunds());
            }
        }
        log.debug("Loan List and the rates {}" , rateCard);

        if (amount.compareTo(BigDecimal.valueOf(0.0)) != 0) {
            log.error("Insufficient funds in the market, requested = {}, available = {}" ,
                    totalLoan, totalLoan.subtract(amount));
            throw new InsufficientFundException("Insufficient funds in the market");
        }

        List<BigDecimal> perLoanWeightFactorList = loanAmountList.stream()
                .map(x -> x.multiply(BigDecimal.valueOf(rateCard.get(x))))
                .collect(Collectors.toList());
        log.debug("Per Loan weight Factor list = {}", perLoanWeightFactorList);
        BigDecimal totalLoanWeightFactor = perLoanWeightFactorList.stream()
                .reduce(BigDecimal::add).get();
        log.debug("Final rate = {}" , totalLoanWeightFactor.divide(totalLoan, BigDecimal.ROUND_DOWN)
                .multiply(new BigDecimal(100)).doubleValue());
        return totalLoanWeightFactor.divide(totalLoan, BigDecimal.ROUND_DOWN)
                .multiply(new BigDecimal(100)).doubleValue();
    }

    @Override
    public BigDecimal calculateAmount(BigDecimal amount, Double rate) {
        throw new RateCalculatorException("Operation Not Supported", new Throwable("Calculate Amount method not supported for COF Calculator"));
    }
}
