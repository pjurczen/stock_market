package stockMarket.components.impl;

import java.util.Set;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import stockMarket.components.StockMarket;
import stockMarket.model.StockTo;

@Component("stockMarket")
public class StockMarketImpl implements StockMarket {

    @Override
    public Set<StockTo> getStockPrices(LocalDate date) {
        // TODO Auto-generated method stub
        return null;
    }

}
