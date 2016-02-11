package stockMarket.strategy;

import java.util.Set;

import stockMarket.model.CashTo;
import stockMarket.model.StockTo;

public interface Strategy {
    boolean confirmBuyingDecision(StockTo stock);
    boolean confirmSellingDecision(StockTo stock);
    StockTo chooseStockToBuy(Set<StockTo> stocks, CashTo myCash);
    StockTo chooseStockToSell(Set<StockTo> stocks, Set<StockTo> clientStocks);
}
