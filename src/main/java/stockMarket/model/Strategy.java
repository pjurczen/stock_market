package stockMarket.model;

import java.util.Set;

public interface Strategy {
    Stock chooseStockToBuy(Set<Stock> stocks);
    Stock chooseStockToSell(Set<Stock> stocks, Set<Stock> clientStocks);
}
