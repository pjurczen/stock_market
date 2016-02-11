package stockMarket.data;

import java.util.Set;

import stockMarket.model.StockTo;

public interface StockData {
    void saveStockData();
    Set<StockTo> getStockPrices();
}
