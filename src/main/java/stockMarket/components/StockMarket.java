package stockMarket.components;

import java.util.Set;

import stockMarket.model.StockTo;

public interface StockMarket {

    Set<StockTo> getStockPrices();

}