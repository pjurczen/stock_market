package stockMarket.components;

import org.joda.time.LocalDate;

public interface StockMarketSimulator {
    LocalDate getDate();
    void nextDay();
    void setStartDay(LocalDate date);
    void setEndDay(LocalDate endDate);
    void notifyAllObservers();
    void register(Observer observer);
}
