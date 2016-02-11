package stockMarket.components.impl;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import stockMarket.components.Observer;
import stockMarket.components.StockMarketSimulator;

@Component("stockMarketSimulator")
public class StockMarketSimulatorImpl implements StockMarketSimulator {

    private LocalDate date;
    private LocalDate endDate;
    private List<Observer> observers;
    
    public StockMarketSimulatorImpl() {
        observers = new ArrayList<Observer>();
    }
    
    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public void nextDay() {
        date = date.plusDays(1);
        if(!date.equals(endDate)) {
            notifyAllObservers();
        }
    }

    @Override
    public void setStartDay(LocalDate date) {
        this.date = date;
    }

    @Override
    public void setEndDay(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public void notifyAllObservers() {
        observers.forEach(x -> x.update());
    }

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }
}
