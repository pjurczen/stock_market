package stockMarket.model;

public class Cash {
    private String currency;
    private double ratio;
    private long ammount;
    
    public Cash(String currency, double ratio, long ammount) {
        this.currency = currency;
        this.ratio = ratio;
        this.ammount = ammount;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public double getRatio() {
        return ratio;
    }
    
    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
    
    public long getAmmount() {
        return ammount;
    }
    
    public void setAmmount(long ammount) {
        this.ammount = ammount;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((currency == null) ? 0 : currency.hashCode());
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
        Cash other = (Cash) obj;
        if (currency == null) {
            if (other.currency != null)
                return false;
        } else if (!currency.equals(other.currency))
            return false;
        return true;
    }
}
