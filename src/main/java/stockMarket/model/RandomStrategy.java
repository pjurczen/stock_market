package stockMarket.model;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service("strategy")
public class RandomStrategy implements Strategy {

    @Override
    public Stock chooseStockToBuy(Set<Stock> stocks) {
        List<Stock> stocksList = stocks.stream().collect(Collectors.toList());
        Collections.shuffle(stocksList);
        int stocksIndexToBuy = (int)(Math.random()*stocks.size());
        String stocksNameToBuy = stocksList.get(stocksIndexToBuy).getCompanyName();
        double stocksPrice = stocksList.get(stocksIndexToBuy).getValue();
        long stocksAmmountToBuy = (long)(Math.random()*stocksList.get(stocksIndexToBuy).getAmmount());
        return new Stock(stocksNameToBuy, stocksPrice, stocksAmmountToBuy);
    }

    @Override
    public Stock chooseStockToSell(Set<Stock> stocks, Set<Stock> clientStocks) {
        List<Stock> stocksList = stocks.stream().collect(Collectors.toList());
        Collections.shuffle(stocksList);
        int stocksIndexToSell = (int)(Math.random()*stocks.size());
        String stocksNameToSell = null;
        double stocksPrice = 0;
        long stocksAmmountToSell = 0;
        if(clientStocks.contains(stocksList.get(stocksIndexToSell))) {
            stocksNameToSell = stocksList.get(stocksIndexToSell).getCompanyName();
            stocksPrice = stocksList.get(stocksIndexToSell).getValue();
            stocksAmmountToSell = (long)(Math.random()*stocksList.get(stocksIndexToSell).getAmmount());
        }
        return new Stock(stocksNameToSell, stocksPrice, stocksAmmountToSell);
    }
}
