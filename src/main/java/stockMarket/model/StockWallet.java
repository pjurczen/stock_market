package stockMarket.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class StockWallet {

    private Map<Stock, Long> account;

    public StockWallet() {
        this.account = new HashMap<Stock, Long>();
    }
    
    public Map<Stock, Long> getStocks() {
        return new HashMap<Stock, Long>(account);
    }

    public void removeStocks(Stock stocks) {
        if(account.get(stocks).compareTo(stocks.getAmmount()) == -1) {
            account.replace(stocks, account.get(stocks) - stocks.getAmmount());
        } else {
            account.remove(stocks);
        }
    }

    public void addStocks(Stock stocks) {
        if(account.containsKey(stocks)) {
            account.replace(stocks, account.get(stocks) + stocks.getAmmount());
        } else {
            account.put(stocks, stocks.getAmmount());
        }
    }
}
