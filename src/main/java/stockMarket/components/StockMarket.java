package stockMarket.components;

import java.util.Set;

import org.joda.time.LocalDate;

import stockMarket.model.StockTo;

public interface StockMarket {

    Set<StockTo> getStockPrices(LocalDate date);

}