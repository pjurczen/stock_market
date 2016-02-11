package stockMarket.components;

import org.joda.time.LocalDate;

public interface StockMarketSimulator {
    LocalDate getDate();
    void nextDay();
    void setStartDate(LocalDate date);
    void setEndDate(LocalDate endDate);
    void notifyAllObservers();
    void register(Observer observer);
}
