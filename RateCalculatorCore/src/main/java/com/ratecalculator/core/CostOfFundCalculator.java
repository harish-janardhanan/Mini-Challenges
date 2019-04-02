package com.ratecalculator.core;

import com.ratecalculator.core.exception.InsufficientFundException;
import com.ratecalculator.marketdata.MarketData;
import com.ratecalculator.marketdata.MarketDataCsvAO;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

final class CostOfFundCalculator implements ICalculator {

    private final MarketDataCsvAO marketDataCsvAO;

    CostOfFundCalculator(String csvPath){
        this.marketDataCsvAO = new MarketDataCsvAO(csvPath);
    }

    @Override
    public Double calculate(BigDecimal amount) {

        Map<BigDecimal,Double>  rateCard = new HashMap<>();
        List<BigDecimal> loanAmountList = new ArrayList<>();
        List<MarketData> marketData = marketDataCsvAO.getAll();
        BigDecimal totalLoan = amount;
        Collections.sort(marketData);

        for (MarketData mktData : marketData){
            if (amount.compareTo(mktData.getAvailableFunds()) <= 0 ){
                rateCard.put(amount,mktData.getLendingRate());
                loanAmountList.add(amount);
                amount = amount.subtract(amount);
                break;
            }else {
                rateCard.put(mktData.getAvailableFunds(), mktData.getLendingRate());
                loanAmountList.add(mktData.getAvailableFunds());
                amount = amount.subtract(mktData.getAvailableFunds());
            }
        }

        if(amount.compareTo(BigDecimal.valueOf(0.0)) != 0){
            throw new InsufficientFundException("Insufficient funds in the market");
        }

        List<BigDecimal> perLoanWeightFactorList = loanAmountList.stream()
                .map(x -> x.multiply(BigDecimal.valueOf(rateCard.get(x))))
                .collect(Collectors.toList());

        BigDecimal totalLoanWeightFactor = perLoanWeightFactorList.stream()
                .reduce(BigDecimal::add).get();

        return totalLoanWeightFactor.divide(totalLoan,BigDecimal.ROUND_DOWN)
                .multiply(new BigDecimal(100)).doubleValue();
    }

    @Override
    public BigDecimal calculateAmount(BigDecimal amount, Double rate) {
        throw new InsufficientFundException("Operation Not Supported");
    }
}
