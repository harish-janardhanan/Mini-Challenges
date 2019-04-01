package com.ratecalculator.marketdata;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class MarketDataTest {
    MarketData marketData = new MarketData();

    @Test
    public void testSetters(){
        marketData.setAvailableFunds(BigDecimal.valueOf(12.0));
        marketData.setLenderName("RichLender");
        marketData.setLendingRate(0.123);

        Assert.assertEquals(marketData.getAvailableFunds(), BigDecimal.valueOf(12.0));
        Assert.assertEquals(marketData.getLenderName(),"RichLender");
        Assert.assertEquals(marketData.getLendingRate(),Double.valueOf(0.123));
    }
}