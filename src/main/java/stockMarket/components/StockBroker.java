package stockMarket.components;

import java.util.Set;

import org.joda.time.LocalDate;

import stockMarket.model.CashTo;
import stockMarket.model.StockTo;

public interface StockBroker {
    Set<StockTo> getStockPrices(LocalDate date);
    StockTo getSellingOffer(StockTo stock);
    StockTo getBuyingOffer(StockTo stock);
    CashTo sellStock();
    CashTo buyStock();
}
