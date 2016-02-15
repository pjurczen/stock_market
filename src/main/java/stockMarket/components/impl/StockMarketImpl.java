package stockMarket.components.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockMarket.components.StockMarket;
import stockMarket.data.StockData;
import stockMarket.to.DataTo;

@Component("stockMarket")
public class StockMarketImpl implements StockMarket {

    private StockData stockData;
    
    @Autowired
    public StockMarketImpl(StockData stockData) {
        this.stockData = stockData;
    }
    
    @Override
    public DataTo getStockPrices() {
        return stockData.getStockPrices();
    }

}
