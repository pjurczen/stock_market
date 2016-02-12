package stockMarket.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import stockMarket.to.StockTo;

@Component
public class StockWallet {

    private Map<StockTo, Long> account;

    public StockWallet() {
        this.account = new HashMap<StockTo, Long>();
    }
    
    public Set<StockTo> getStocks() {
        return account.keySet();
    }

    public void removeStocks(StockTo stocks) {
        if(account.get(stocks) > stocks.getAmmount()) {
            long stocksAmmountLeft = account.get(stocks) - stocks.getAmmount();
            StockTo stocksLeft = new StockTo(stocks.getCompanyName(), stocks.getValue(), stocksAmmountLeft);
            account.remove(stocks);
            account.put(stocksLeft, stocksAmmountLeft);
        } else {
            account.remove(stocks);
        }
    }

    public void addStocks(StockTo stocks) {
        if(account.containsKey(stocks)) {
            long ammountToPut = account.get(stocks) + stocks.getAmmount();
            StockTo stocksToPut = new StockTo(stocks.getCompanyName(),stocks.getValue(), ammountToPut);
            account.remove(stocks);
            account.put(stocksToPut, ammountToPut);
        } else {
            account.put(stocks, stocks.getAmmount());
        }
    }
}
