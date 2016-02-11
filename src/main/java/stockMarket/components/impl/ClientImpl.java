package stockMarket.components.impl;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockMarket.components.Client;
import stockMarket.components.Observer;
import stockMarket.components.StockBroker;
import stockMarket.model.CashTo;
import stockMarket.model.CashWallet;
import stockMarket.model.StockTo;
import stockMarket.model.StockWallet;
import stockMarket.strategy.Strategy;

@Component("client")
public class ClientImpl extends Observer implements Client {
    
    private CashWallet cashWallet;
    private StockWallet stockWallet;
    private Strategy strategy;
    private StockBroker stockBroker;
    private Set<StockTo> currentStocksInMarket;
    
    @Autowired
    public ClientImpl(CashWallet cashWallet, StockWallet stockWallet,
            Strategy strategy, StockBroker stockBroker) {
        this.cashWallet = cashWallet;
        this.stockWallet = stockWallet;
        this.strategy = strategy;
        this.stockBroker = stockBroker;
    }
    
    @PostConstruct
    public void setUp() {
        try {
            currentStocksInMarket = stockBroker.getStockPrices();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        setUp();
        sell();
        buy();
        stockMarketSimulator.nextDay();
    }

    @Override
    public void buy() {
        CashTo myCash = cashWallet.getBalanceInPLN();
        StockTo stockToBuy = strategy.chooseStockToBuy(currentStocksInMarket, myCash);
        while(stockToBuy.getCompanyName() != null) {
            StockTo stockToBuyOffered = stockBroker.getBuyingOffer(stockToBuy);
            if(strategy.confirmBuyingDecision(stockToBuyOffered)) {
                cashWallet.withdraw(stockBroker.buyStock());
                stockWallet.addStocks(stockToBuyOffered);
            }
            myCash = cashWallet.getBalanceInPLN();
            stockToBuy = strategy.chooseStockToBuy(currentStocksInMarket, myCash);
        }
    }

    @Override
    public void sell() {
        Set<StockTo> myStocks = stockWallet.getStocks();
        StockTo stockToSell = strategy.chooseStockToSell(currentStocksInMarket, myStocks);
        while(stockToSell.getCompanyName() != null) {
            StockTo stockToSellOffered = stockBroker.getSellingOffer(stockToSell);
            if(strategy.confirmSellingDecision(stockToSellOffered)) {
                cashWallet.deposit(stockBroker.sellStock());
                stockWallet.removeStocks(stockToSellOffered);
            }
            myStocks = stockWallet.getStocks();
            stockToSell = strategy.chooseStockToSell(currentStocksInMarket, myStocks);
        }
    }

    @Override
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    
}
