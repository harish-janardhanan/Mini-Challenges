package com.ratecalculator.marketdata;

import java.math.BigDecimal;
import java.util.Objects;

public class MarketData implements Comparable<MarketData> {
    private String lenderName;
    private Double lendingRate;
    private BigDecimal availableFunds;

    public MarketData(String lenderName, Double lendingRate, BigDecimal availableFunds){
        this.lenderName = lenderName;
        this.lendingRate = lendingRate;
        this.availableFunds = availableFunds;
    }

    public MarketData(){
        //Default Constructor
    }

    public String getLenderName() {
        return lenderName;
    }

    public void setLenderName(String lenderName) {
        this.lenderName = lenderName;
    }

    public Double getLendingRate() {
        return lendingRate;
    }

    public void setLendingRate(Double lendingRate) {
        this.lendingRate = lendingRate;
    }

    public BigDecimal getAvailableFunds() {
        return availableFunds;
    }

    public void setAvailableFunds(BigDecimal availableFunds) {
        this.availableFunds = availableFunds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarketData that = (MarketData) o;
        return lendingRate.equals(that.lendingRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lendingRate);
    }

    @Override
    public String toString() {
        return "MarketData{" +
                "lenderName='" + lenderName + '\'' +
                ", lendingRate=" + lendingRate +
                ", availableFunds=" + availableFunds +
                '}';
    }

    @Override
    public int compareTo(MarketData o) {
        if (this.equals(o))
            return 0;
        else if (this.lendingRate > o.lendingRate)
            return 1;
        else
            return -1;
    }
}
