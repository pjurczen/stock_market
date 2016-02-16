package stockMarket.components;

import org.joda.time.LocalDate;

import stockMarket.components.impl.Observer;
import stockMarket.exceptions.NoEndDateSpecifiedException;

public interface StockMarketSimulator {
    LocalDate getDate();
    void nextDay() throws NoEndDateSpecifiedException;
    void setStartDate(LocalDate date);
    void setEndDate(LocalDate endDate);
    void notifyAllObservers();
    void register(Observer observer);
    void skipDate();
}
