package stockMarket.to;

import java.util.Set;

import org.joda.time.LocalDate;

public class DataTo {
    
    private Set<StockTo> stocks;
    private LocalDate date;
    
    public DataTo(Set<StockTo> stocks, LocalDate date) {
        this.stocks = stocks;
        this.date = date;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public Set<StockTo> getStocks() {
        return stocks;
    }
    
    public void setStocks(Set<StockTo> stocks) {
        this.stocks = stocks;
    }
}
