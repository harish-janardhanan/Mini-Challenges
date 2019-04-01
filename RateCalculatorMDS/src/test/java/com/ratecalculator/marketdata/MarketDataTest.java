package com.ratecalculator.marketdata;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Objects;

public class MarketDataTest {


    @Test
    public void testSetters(){
        MarketData marketData = new MarketData();
        marketData.setAvailableFunds(BigDecimal.valueOf(12.0));
        marketData.setLenderName("RichLender");
        marketData.setLendingRate(0.123);

        Assert.assertEquals(marketData.getAvailableFunds(), BigDecimal.valueOf(12.0));
        Assert.assertEquals(marketData.getLenderName(),"RichLender");
        Assert.assertEquals(marketData.getLendingRate(),Double.valueOf(0.123));
    }

    @Test
    public void testHashCode(){
        MarketData marketData = new MarketData();
        marketData.setAvailableFunds(BigDecimal.valueOf(12.0));
        marketData.setLenderName("RichLender");
        marketData.setLendingRate(0.123);

        Assert.assertEquals(marketData.hashCode(), Objects.hash(0.123));
    }

    @Test
    public void testEquals(){
        MarketData marketData = new MarketData();
        MarketData marketData1 = new MarketData();

        marketData.setAvailableFunds(BigDecimal.valueOf(12.0));
        marketData.setLenderName("RichLender");
        marketData.setLendingRate(0.123);

        marketData1.setAvailableFunds(BigDecimal.valueOf(23.0));
        marketData1.setLenderName("RichLender");
        marketData1.setLendingRate(0.123);

        Assert.assertTrue(marketData.equals(marketData1));


    }

    @Test
    public void testCompareTo(){
        MarketData marketData = new MarketData();
        MarketData marketData1 = new MarketData();
        MarketData marketData2 = new MarketData();
        MarketData marketData3 = new MarketData();


        marketData.setAvailableFunds(BigDecimal.valueOf(12.0));
        marketData.setLenderName("RichLender");
        marketData.setLendingRate(0.123);

        marketData1.setAvailableFunds(BigDecimal.valueOf(23.0));
        marketData1.setLenderName("RichLender");
        marketData1.setLendingRate(0.123);

        marketData2.setAvailableFunds(BigDecimal.valueOf(23.0));
        marketData2.setLenderName("RichLender");
        marketData2.setLendingRate(0.2);

        marketData3.setAvailableFunds(BigDecimal.valueOf(23.0));
        marketData3.setLenderName("RichLender");
        marketData3.setLendingRate(0.01);

        Assert.assertEquals(marketData.compareTo(marketData1),0);
        Assert.assertEquals(marketData.compareTo(marketData2), -1);
        Assert.assertEquals(marketData.compareTo(marketData3), 1);
    }

    @Test
    public void testToString(){
        MarketData marketData = new MarketData();

        marketData.setAvailableFunds(BigDecimal.valueOf(12.0));
        marketData.setLenderName("RichLender");
        marketData.setLendingRate(0.123);

        Assert.assertEquals(marketData.toString(),"MarketData{" +
                "lenderName='" + "RichLender" + '\'' +
                ", lendingRate=" + 0.123 +
                ", availableFunds=" + 12.0 +
                '}');
    }

}