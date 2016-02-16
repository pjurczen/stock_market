package stockMarket.strategy.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import stockMarket.strategy.Strategy;
import stockMarket.to.CashTo;
import stockMarket.to.DataTo;
import stockMarket.to.StockTo;

@Service("sellFallingBuyRisingStrategy")
public class SellFallingBuyRisingStrategy implements Strategy {

    private List<DataTo> dataHistory = new ArrayList<DataTo>();
    private Set<StockTo> stocksToBuy;
    private Set<StockTo> stocksToSell;
    
    @Override
    public boolean confirmBuyingDecision(StockTo stock) {
        return (stock.getValue() <= stocksToBuy.stream()
                .filter(x -> x.equals(stock))
                .findFirst()
                .get()
                .getValue()*1.01) ? true : false;
    }

    @Override
    public boolean confirmSellingDecision(StockTo stock) {
        return (stock.getValue() >= stocksToSell.stream()
                .filter(x -> x.equals(stock))
                .findFirst()
                .get()
                .getValue()*0.99) ? true : false;
    }

    @Override
    public Set<StockTo> chooseStocksToBuy(DataTo stocksData, CashTo myCash) {
        if(!dataHistory.contains(stocksData)) {
            dataHistory.add(stocksData);
        }
        stocksToBuy = new HashSet<StockTo>();
        if(dataHistory.size() >= 3) {
            Collections.sort(dataHistory);
            DataTo yesterdaysData = dataHistory.get(1);
            DataTo dayBeforeYesterdayData = dataHistory.get(2);
            for(StockTo todaysStock : stocksData.getStocks()) {
                StockTo yesterdaysStock = yesterdaysData.getStocks().stream().filter(x -> x.equals(todaysStock)).findFirst().get();
                StockTo dayBeforeYesterdayStock = dayBeforeYesterdayData.getStocks().stream().filter(x -> x.equals(todaysStock)).findFirst().get();
                if(checkIfPricesIsAscending(todaysStock, yesterdaysStock, dayBeforeYesterdayStock)) {
                    stocksToBuy.add(todaysStock);
                }
            }
        }
        return stocksToBuy;
    }

    @Override
    public Set<StockTo> chooseStocksToSell(DataTo stocksData, Set<StockTo> clientStocks) {
        if(!dataHistory.contains(stocksData)) {
            dataHistory.add(stocksData);
        }
        stocksToSell = new HashSet<StockTo>();
        if(dataHistory.size() >= 3) {
            Collections.sort(dataHistory);
            DataTo todaysData = dataHistory.get(0);
            DataTo yesterdaysData = dataHistory.get(1);
            DataTo dayBeforeYesterdayData = dataHistory.get(2);
            for(StockTo stock : clientStocks) {
                StockTo todaysStock = todaysData.getStocks().stream().filter(x -> x.equals(stock)).findFirst().get();
                StockTo yesterdaysStock = yesterdaysData.getStocks().stream().filter(x -> x.equals(stock)).findFirst().get();
                StockTo dayBeforeYesterdayStock = dayBeforeYesterdayData.getStocks().stream().filter(x -> x.equals(stock)).findFirst().get();
                if(checkIfPriceIsDescending(todaysStock, yesterdaysStock, dayBeforeYesterdayStock)) {
                    stocksToSell.add(stock);
                }
            }
        }
        return stocksToSell;
    }
    
    private boolean checkIfPriceIsDescending(StockTo todaysStock, StockTo yesterdaysStock, StockTo dayBeforeYesterdayStock) {
        if(yesterdaysStock != null && todaysStock != null) {
            if(todaysStock.getValue() >= yesterdaysStock.getValue()) {
                return false;
            }
        }
        if(dayBeforeYesterdayStock != null && todaysStock != null) {
            if(todaysStock.getValue() >= dayBeforeYesterdayStock.getValue()) {
                return false;
            }
        }
        if(dayBeforeYesterdayStock != null && yesterdaysStock != null) {
            if(yesterdaysStock.getValue() >= dayBeforeYesterdayStock.getValue()) {
                return false;
            }
        }
        return true;
    }
    
    private boolean checkIfPricesIsAscending(StockTo todaysStock, StockTo yesterdaysStock, StockTo dayBeforeYesterdayStock) {
        if(yesterdaysStock != null) {
            if(todaysStock.getValue() <= yesterdaysStock.getValue()) {
                return false;
            }
        }
        if(dayBeforeYesterdayStock != null) {
            if(todaysStock.getValue() <= dayBeforeYesterdayStock.getValue()) {
                return false;
            }
        }
        if(dayBeforeYesterdayStock != null && yesterdaysStock != null) {
            if(yesterdaysStock.getValue() <= dayBeforeYesterdayStock.getValue()) {
                return false;
            }
        }
        return true;
    }
}
