package stockMarket.components;

import java.util.Set;

import stockMarket.to.StockTo;

public interface StockMarket {

    Set<StockTo> getStockPrices();

}