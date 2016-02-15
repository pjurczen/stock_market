package stockMarket.to;

import java.util.Set;

import org.joda.time.LocalDate;

public class DataTo implements Comparable<DataTo> {
    
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((stocks == null) ? 0 : stocks.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DataTo other = (DataTo) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (stocks == null) {
            if (other.stocks != null)
                return false;
        } else if (!stocks.equals(other.stocks))
            return false;
        return true;
    }

    @Override
    public int compareTo(DataTo o) {
        if(date.isBefore(o.getDate())) {
            return 1;
        }
        if(date.isAfter(o.getDate())) {
            return -1;
        }
        return 0;
    }
    
}
