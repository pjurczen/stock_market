package stockMarket.components.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockMarket.components.Client;
import stockMarket.components.StockBroker;
import stockMarket.model.CashWallet;
import stockMarket.model.StockWallet;
import stockMarket.model.Strategy;

@Component("client")
public class ClientImpl implements Client {
    
    private CashWallet cashWallet;
    private StockWallet stockWallet;
    private Strategy strategy;
    private StockBroker stockBroker;
    
    @Autowired
    public ClientImpl(CashWallet cashWallet, StockWallet stockWallet, Strategy strategy, StockBroker stockBroker) {
        this.cashWallet = cashWallet;
        this.stockWallet = stockWallet;
        this.strategy = strategy;
    }
    
}
