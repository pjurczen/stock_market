package stockMarket.components.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockMarket.components.StockBroker;
import stockMarket.components.StockMarket;
import stockMarket.to.CashTo;
import stockMarket.to.DataTo;
import stockMarket.to.StockTo;

@Component("stockBroker")
public class StockBrokerImpl implements StockBroker {

    private static final double AMMOUNT_FLUCTUATION = 0.2;
    private static final double PRICE_FLUCTUATION = 0.02;
    private StockMarket stockMarket;
    private StockTo currentSellingOffer;
    private StockTo currentBuyingOffer;

    @Autowired
    public StockBrokerImpl(StockMarket stockMarket) {
        this.stockMarket = stockMarket;
    }

    @Override
    public DataTo getStockPrices() {
        return stockMarket.getStockPrices();
    }

    @Override
    public StockTo getSellingOffer(StockTo stock) {
        long offeredAmmount = (long) (stock.getAmmount() * getFluctuation());
        double offeredPrice = stock.getValue() * (1 - PRICE_FLUCTUATION * Math.random());
        currentSellingOffer = new StockTo(stock.getCompanyName(), offeredPrice, offeredAmmount);
        return currentSellingOffer;
    }
    @Override
    public StockTo getBuyingOffer(StockTo stock) {
        long offeredAmmount = (long) (stock.getAmmount() * getFluctuation());
        double offeredPrice = stock.getValue() * (1 + PRICE_FLUCTUATION * Math.random());
        currentBuyingOffer = new StockTo(stock.getCompanyName(), offeredPrice, offeredAmmount);
        return currentBuyingOffer;
    }

    private double getFluctuation() {
        return (1 - AMMOUNT_FLUCTUATION) + AMMOUNT_FLUCTUATION * Math.random();
    }

    @Override
    public CashTo sellStock() {
        return new CashTo("PLN", 1, (long) (currentSellingOffer.getAmmount() * currentSellingOffer.getValue()));
    }

    @Override
    public CashTo buyStock() {
        return new CashTo("PLN", 1, (long) (currentBuyingOffer.getAmmount() * currentBuyingOffer.getValue()));
    }

}
