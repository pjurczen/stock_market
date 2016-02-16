package stockMarket.dataLoader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.joda.time.IllegalFieldValueException;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import stockMarket.to.StockTo;

@Component
public class DataLoader {
    
    public Map<LocalDate, Set<StockTo>> loadStocksFromFile(String filePath) {
        Map<LocalDate, Set<StockTo>> stocks = new HashMap<LocalDate, Set<StockTo>>();
        try {
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                Set<StockTo> stocksSet = new HashSet<StockTo>();
                String[] splittedText = line.split(",");
                int year = Integer.parseInt(splittedText[1].substring(0, 4));
                int month = parseMonth(splittedText[1]);
                int day = parseDay(splittedText[1]);
                String stockName = splittedText[0];
                double stockValue = Double.parseDouble(splittedText[2]);
                LocalDate date = new LocalDate(year, month, day);
                StockTo stock = new StockTo(stockName, stockValue, 0);
                
//                if(!stocks.containsKey(date))
//                    stocks.put(date, new HashSet<StockTo>());
//                
//                stocks.get(date).add(stock);
                
                if(stocks.containsKey(date)) {
                    stocksSet = stocks.get(date);
                    stocks.remove(date);
                }
                stocksSet.add(stock);
                stocks.put(date, stocksSet);
            }
            reader.close();
            inputStream.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return stocks;
    }
    
    public int parseMonth(String monthString) {
        try {
            return Integer.parseInt(monthString.substring(4, 6));
        } catch (IllegalFieldValueException e) {
            return Integer.parseInt(monthString.substring(5, 6));
        }
    }
    
    public int parseDay(String dayString) {
        try {
            return Integer.parseInt(dayString.substring(6, 8));
        } catch (IllegalFieldValueException e) {
            return Integer.parseInt(dayString.substring(7, 8));
        }
    }
}
