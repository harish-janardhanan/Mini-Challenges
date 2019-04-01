package com.ratecalculator.marketdata.csv;

import com.ratecalculator.marketdata.MarketData;
import com.ratecalculator.marketdata.exception.MarketDataReaderException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class MarketDataCSVReaderTest {

    MarketDataCSVReader marketDataCSVReader = new MarketDataCSVReader("\"src/test/resources/MarketDataforExercise.csv\"");

    @Test
    public void testReadFile(){
        List<MarketData> marketData = marketDataCSVReader.getMarketDataFromCsv();
        Assert.assertNotNull(marketData);
    }

    @Test
    public void testSingleGet(){
        MarketData data = marketDataCSVReader.getMarketDataByLender("Bob");

        Assert.assertEquals("Bob", data.getLenderName());
        Assert.assertEquals(Double.valueOf(.075D),data.getLendingRate());
        Assert.assertEquals(BigDecimal.valueOf(640.0),data.getAvailableFunds());
    }

    @Test(expected = MarketDataReaderException.class)
    public void testExceptionIsThrownWhenNoFileFound(){
        MarketDataCSVReader exceptionReader = new MarketDataCSVReader("wrong/path");
        exceptionReader.getMarketDataFromCsv();
    }

    @Test(expected = MarketDataReaderException.class)
    public void testExceptionIsThrownWhenParsingError(){
        MarketDataCSVReader exceptionReader = new MarketDataCSVReader("src/test/resources/MarketDataforExerciseParseErr~or.csv");
        exceptionReader.getMarketDataFromCsv();
    }

}
