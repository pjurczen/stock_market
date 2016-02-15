package stockMarket.strategy.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import stockMarket.strategy.Strategy;
import stockMarket.to.CashTo;
import stockMarket.to.DataTo;
import stockMarket.to.StockTo;

@Service("strategy")
public class RandomStrategy implements Strategy {

    private Set<StockTo> stocksToBuy;
    private Set<StockTo> stocksToSell;
    
    @Override
    public Set<StockTo> chooseStocksToBuy(DataTo stocksData, CashTo myCash) {
        stocksToBuy = new HashSet<StockTo>();
        for(StockTo stock : stocksData.getStocks()) {
            if(Math.random() > 0.5) {
                String stockName = stock.getCompanyName();
                double stockPrice = stock.getValue();
                StockTo stockToBuy = new StockTo(stockName, stockPrice, 0);
                stocksToBuy.add(stockToBuy);
            }
        }
        return stocksToBuy;
    }

    @Override
    public Set<StockTo> chooseStocksToSell(DataTo stocksData, Set<StockTo> clientStocks) {
        stocksToSell = new HashSet<StockTo>();
        for(StockTo stock : clientStocks) {
            if(Math.random() > 0.5) {
                String stockName = stock.getCompanyName();
                double stockPrice = stock.getValue();
                StockTo stockToSell = new StockTo(stockName, stockPrice, 0);
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
