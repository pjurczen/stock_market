package stockMarket.strategy.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import stockMarket.strategy.Strategy;
import stockMarket.to.CashTo;
import stockMarket.to.StockTo;

@Service("strategy")
public class RandomStrategy implements Strategy {

    private Set<StockTo> stocksToBuy;
    private Set<StockTo> stocksToSell;
    
    @Override
    public Set<StockTo> chooseStocksToBuy(Set<StockTo> stocks, CashTo myCash) {
        stocksToBuy = new HashSet<StockTo>();
        for(StockTo stock : stocks) {
            if(Math.random() > 0.5) {
                String stockName = stock.getCompanyName();
                double stockPrice = stock.getValue();
                long stockAmmount = (long)(Math.random()*myCash.getAmmount()/stockPrice);
                StockTo stockToBuy = new StockTo(stockName, stockPrice, stockAmmount);
                stocksToBuy.add(stockToBuy);
            }
        }
        return stocksToBuy;
    }

    @Override
    public Set<StockTo> chooseStocksToSell(Set<StockTo> stocks, Set<StockTo> clientStocks) {
        stocksToSell = new HashSet<StockTo>();
        for(StockTo stock : clientStocks) {
            if(Math.random() > 0.5) {
                String stockName = stock.getCompanyName();
                double stockPrice = stock.getValue();
                long stockAmmount = (long)(Math.random()*stock.getAmmount());
                StockTo stockToSell = new StockTo(stockName, stockPrice, stockAmmount);
                stocksToSell.add(stockToSell);
            }
        }
        return stocksToSell;
    }

    @Override
    public boolean confirmBuyingDecision(StockTo stock) {
        return (stock.getValue() <= stocksToBuy.stream()
                                                    .filter(x -> x.equals(stock))
                                                    .findFirst()
                                                    .get()
                                                    .getValue()*1.01) ? true : false;
    }

    @Override
    public boolean confirmSellingDecision(StockTo stock) {
        return (stock.getValue() >= stocksToSell.stream()
                                                    .filter(x -> x.equals(stock))
                                                    .findFirst()
                                                    .get()
                                                    .getValue()*0.99) ? true : false;
    }
}
