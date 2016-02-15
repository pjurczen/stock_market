package stockMarket.components;

import stockMarket.to.CashTo;
import stockMarket.to.DataTo;
import stockMarket.to.StockTo;

public interface StockBroker {
    DataTo getStockPrices();
    StockTo getSellingOffer(StockTo stock);
    StockTo getBuyingOffer(StockTo stock);
    CashTo sellStock();
    CashTo buyStock();
}
