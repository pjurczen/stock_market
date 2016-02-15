package stockMarket.components.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockMarket.components.Client;
import stockMarket.components.Observer;
import stockMarket.components.StockBroker;
import stockMarket.exceptions.NoEndDateSpecifiedException;
import stockMarket.model.CashWallet;
import stockMarket.model.StockWallet;
import stockMarket.strategy.Strategy;
import stockMarket.to.CashTo;
import stockMarket.to.DataTo;
import stockMarket.to.StockTo;

@Component("client")
public class ClientImpl extends Observer implements Client {
    
    private CashWallet cashWallet;
    private StockWallet stockWallet;
    private Strategy strategy;
    private StockBroker stockBroker;
    private DataTo currentStocksInMarket;
    
    @Autowired
    public ClientImpl(CashWallet cashWallet, StockWallet stockWallet,
            Strategy strategy, StockBroker stockBroker) {
        this.cashWallet = cashWallet;
        this.stockWallet = stockWallet;
        this.strategy = strategy;
        this.stockBroker = stockBroker;
    }
    
    @Override
    public void update() {
        sell();
        buy();
        try {
            stockMarketSimulator.nextDay();
        } catch (NoEndDateSpecifiedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buy() {
        currentStocksInMarket = stockBroker.getStockPrices();
        CashTo myCash = cashWallet.getBalanceInPLN();
        Set<StockTo> stocksToBuy = strategy.chooseStocksToBuy(currentStocksInMarket, myCash);
        for(StockTo stockToBuy : stocksToBuy) {
            StockTo stockToBuyOffered = stockBroker.getBuyingOffer(stockToBuy);
            if(strategy.confirmBuyingDecision(stockToBuyOffered) &&
                    (stockToBuyOffered.getAmmount()*stockToBuyOffered.getValue() < myCash.getAmmount())) {
                cashWallet.withdraw(stockBroker.buyStock());
                stockWallet.addStocks(stockToBuyOffered);
            }
        }
    }

    @Override
    public void sell() {
        currentStocksInMarket = stockBroker.getStockPrices();
        Set<StockTo> myStocks = stockWallet.getStocks();
        Set<StockTo> stocksToSell = strategy.chooseStocksToSell(currentStocksInMarket, myStocks);
        for(StockTo stockToSell : stocksToSell) {
            StockTo stockToSellOffered = stockBroker.getSellingOffer(stockToSell);
            if(strategy.confirmSellingDecision(stockToSellOffered)) {
                cashWallet.deposit(stockBroker.sellStock());
                stockWallet.removeStocks(stockToSellOffered);
            }
        }
    }

    @Override
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public CashTo sumUpCash() {
        Set<StockTo> stocks = new HashSet<StockTo>(stockWallet.getStocks());
        for(StockTo stock : stocks) {
            stockBroker.getSellingOffer(stock);
            cashWallet.deposit(stockBroker.sellStock());
            stockWallet.removeStocks(stock);
        }
        return cashWallet.getBalanceInPLN();
    }
}
