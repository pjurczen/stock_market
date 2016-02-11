package stockMarket.components;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class Observer {
    @Autowired
    protected StockMarketSimulator stockMarketSimulator;
    
    public abstract void update();
}
