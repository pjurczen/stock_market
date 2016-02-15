package stockMarket.strategy;

import java.util.Set;

import stockMarket.to.CashTo;
import stockMarket.to.DataTo;
import stockMarket.to.StockTo;

public interface Strategy {
    boolean confirmBuyingDecision(StockTo stock);
    boolean confirmSellingDecision(StockTo stock);
    Set<StockTo> chooseStocksToBuy(DataTo stocksData, CashTo myCash);
    Set<StockTo> chooseStocksToSell(DataTo stocksData, Set<StockTo> clientStocks);
}
