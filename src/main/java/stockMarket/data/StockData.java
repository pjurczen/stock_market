package stockMarket.data;

import java.util.Map;
import java.util.Set;

import org.joda.time.LocalDate;

import stockMarket.to.DataTo;
import stockMarket.to.StockTo;

public interface StockData {
    void saveStockData(Map<LocalDate, Set<StockTo>> stocks);
    DataTo getStockPrices();
}
