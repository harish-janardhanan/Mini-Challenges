package com.ratecalculator.marketdata;

import com.ratecalculator.marketdata.exception.MarketDataReaderException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class MarketDataCsvAOTest {

    @Test
    public void testGetAllMethod(){
        MarketDataCsvAO marketDataCsvAO = new MarketDataCsvAO("src/test/resources/MarketDataforExercise.csv");

        List<MarketData> marketDataList = marketDataCsvAO.getAll();

        Assert.assertNotNull(marketDataList);
        Assert.assertEquals(marketDataList.size(),7);
    }

    @Test
    public void testgetByLender(){
        MarketDataCsvAO marketDataCsvAO = new MarketDataCsvAO("src/test/resources/MarketDataforExercise.csv");
        MarketData marketData = marketDataCsvAO.get("Bob");

        Assert.assertEquals(marketData.getLendingRate(), Double.valueOf(0.075));
        Assert.assertEquals(marketData.getAvailableFunds(), BigDecimal.valueOf(640.0));
    }

    @Test(expected = MarketDataReaderException.class)
    public void testExceptionWhenFileNotFound(){
        MarketDataCsvAO marketDataCsvAO = new MarketDataCsvAO("WRONGFILE");
        marketDataCsvAO.getAll();
    }
}
