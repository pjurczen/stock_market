package stockMarket.strategy.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import stockMarket.model.CashTo;
import stockMarket.model.StockTo;
import stockMarket.strategy.Strategy;

@Service("strategy")
public class RandomStrategy implements Strategy {

    private StockTo stockToBuy;
    private StockTo stockToSell;
    
    @Override
    public StockTo chooseStockToBuy(Set<StockTo> stocks, CashTo myCash) {
        List<StockTo> stocksList = stocks.stream().collect(Collectors.toList());
        Collections.shuffle(stocksList);
        int stocksIndexToBuy = 2*(int)(Math.random()*stocks.size());
        String stocksNameToBuy = null;
        double stocksPrice = 0;
        long stocksAmmountToBuy = 0;
        if(stocksIndexToBuy < stocks.size()) {
            stocksNameToBuy = stocksList.get(stocksIndexToBuy).getCompanyName();
            stocksPrice = stocksList.get(stocksIndexToBuy).getValue();
            stocksAmmountToBuy = (long)(Math.random()*myCash.getAmmount()/stocksPrice); 
        }
        stockToBuy = new StockTo(stocksNameToBuy, stocksPrice, stocksAmmountToBuy);
        return stockToBuy;
    }

    @Override
    public StockTo chooseStockToSell(Set<StockTo> stocks, Set<StockTo> clientStocks) {
        List<StockTo> stocksList = clientStocks.stream().collect(Collectors.toList());
        Collections.shuffle(stocksList);
        int stocksIndexToSell = 2*(int)(Math.random()*clientStocks.size());
        String stocksNameToSell = null;
        double stocksPrice = 0;
        long stocksAmmountToSell = 0;
        if(stocksIndexToSell < clientStocks.size()) {
            stocksNameToSell = stocksList.get(stocksIndexToSell).getCompanyName();
            stocksPrice = stocksList.get(stocksIndexToSell).getValue();
            stocksAmmountToSell = (long)(Math.random()*stocksList.get(stocksIndexToSell).getAmmount());
        }
        stockToSell = new StockTo(stocksNameToSell, stocksPrice, stocksAmmountToSell);
        return stockToSell;
    }

    @Override
    public boolean confirmBuyingDecision(StockTo stock) {
        return (stock.getValue() <= stockToBuy.getValue()*1.01) ? true : false;
    }

    @Override
    public boolean confirmSellingDecision(StockTo stock) {
        return (stock.getValue() >= stockToSell.getValue()*0.99) ? true : false;
    }
}
