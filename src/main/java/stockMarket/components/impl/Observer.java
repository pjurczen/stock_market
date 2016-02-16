package stockMarket.components.impl;

import org.springframework.beans.factory.annotation.Autowired;

import stockMarket.components.StockMarketSimulator;

public abstract class Observer {
    @Autowired
    protected StockMarketSimulator stockMarketSimulator;
    
    public abstract void update();
}
