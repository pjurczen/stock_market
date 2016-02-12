package stockMarket.strategy;

import java.util.Set;

import stockMarket.to.CashTo;
import stockMarket.to.StockTo;

public interface Strategy {
    boolean confirmBuyingDecision(StockTo stock);
    boolean confirmSellingDecision(StockTo stock);
    Set<StockTo> chooseStocksToBuy(Set<StockTo> stocks, CashTo myCash);
    Set<StockTo> chooseStocksToSell(Set<StockTo> stocks, Set<StockTo> clientStocks);
}
