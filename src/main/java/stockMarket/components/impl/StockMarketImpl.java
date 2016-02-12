package stockMarket.components.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockMarket.components.StockMarket;
import stockMarket.data.StockData;
import stockMarket.to.StockTo;

@Component("stockMarket")
public class StockMarketImpl implements StockMarket {

    private StockData stockData;
    
    @Autowired
    public StockMarketImpl(StockData stockData) {
        this.stockData = stockData;
    }
    
    @Override
    public Set<StockTo> getStockPrices() {
        return stockData.getStockPrices();
    }

}
