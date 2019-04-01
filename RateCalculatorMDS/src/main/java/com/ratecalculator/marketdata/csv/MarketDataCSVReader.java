package com.ratecalculator.marketdata.csv;

import com.ratecalculator.marketdata.MarketData;
import com.ratecalculator.marketdata.exception.MarketDataReaderException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketDataCSVReader {

    private final String csvPath;
    private static final Logger log = LoggerFactory.getLogger(MarketDataCSVReader.class);
    public MarketDataCSVReader( String csvPath){
        this.csvPath = csvPath;
    }
    public List<MarketData> getMarketDataFromCsv() {
        List<MarketData> marketDataList = new ArrayList<>();
        try {

        Iterable<CSVRecord> records;

            Reader csvReader = new FileReader(csvPath);
            records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvReader);
            for (CSVRecord record : records){
                marketDataList.add(new MarketData(record.get(MarketDataHeader.Lender),
                        Double.parseDouble(record.get(MarketDataHeader.Rate)),
                        BigDecimal.valueOf(Double.parseDouble(record.get(MarketDataHeader.Available)))));
            }
        }
        catch (FileNotFoundException f){
            log.error(MessageFormat.format("Market Data Source not available {0}", f.getMessage()));
            throw new MarketDataReaderException(f.getMessage());
         }
        catch (IOException e) {
            log.error(MessageFormat.format("Error Parsing MarketDataFile {0}", e.getMessage()));
            throw  new MarketDataReaderException(e.getMessage());
        }

        return marketDataList;
    }

    public MarketData getMarketDataByLender(String lenderName){
        Map<String, MarketData> marketDataMap = new HashMap<>();

        List<MarketData> marketDataList = getMarketDataFromCsv();

        for (MarketData marketData : marketDataList){
            marketDataMap.put(marketData.getLenderName(), marketData);
        }

        return marketDataMap.get(lenderName);
    }

}
