package stockMarket.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class StockWallet {

    private Map<Stock, Long> account;

    public StockWallet() {
        this.account = new HashMap<Stock, Long>();
    }
    
    public Set<Stock> getStocks() {
        return account.keySet();
    }

    public void removeStocks(Stock stocks) {
        if(account.get(stocks) > stocks.getAmmount()) {
            long stocksAmmountLeft = account.get(stocks) - stocks.getAmmount();
            Stock stocksLeft = new Stock(stocks.getCompanyName(), stocks.getValue(), stocksAmmountLeft);
            account.remove(stocks);
            account.put(stocksLeft, stocksAmmountLeft);
        } else {
            account.remove(stocks);
        }
    }

    public void addStocks(Stock stocks) {
        if(account.containsKey(stocks)) {
            long ammountToPut = account.get(stocks) + stocks.getAmmount();
            Stock stocksToPut = new Stock(stocks.getCompanyName(),stocks.getValue(), ammountToPut);
            account.remove(stocks);
            account.put(stocksToPut, ammountToPut);
        } else {
            account.put(stocks, stocks.getAmmount());
        }
    }
}
