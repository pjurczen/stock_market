package stockMarket.components;

import java.util.Set;

import stockMarket.to.CashTo;
import stockMarket.to.StockTo;

public interface StockBroker {
    Set<StockTo> getStockPrices();
    StockTo getSellingOffer(StockTo stock);
    StockTo getBuyingOffer(StockTo stock);
    CashTo sellStock();
    CashTo buyStock();
}
