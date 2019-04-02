package com.ratecalculator.marketdata;

import com.ratecalculator.marketdata.csv.MarketDataCSVReader;

import java.util.List;

public class MarketDataCsvAO implements MdaoApi<MarketData> {

    private final String csvPath;

    public MarketDataCsvAO(String csvPath) {
        this.csvPath = csvPath;
    }

    @Override
    public List<MarketData> getAll() {

        MarketDataCSVReader marketDataCSVReader = new MarketDataCSVReader(csvPath);
        return marketDataCSVReader.getMarketDataFromCsv();
    }

    @Override
    public MarketData get(String lenderName) {
        MarketDataCSVReader marketDataCSVReader = new MarketDataCSVReader(csvPath);
        return marketDataCSVReader.getMarketDataByLender(lenderName);
    }
}
