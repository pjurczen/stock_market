package stockMarket.model;

public class Stock {
    private String companyName;
    private double value;
    private long ammount;
    
    public Stock(String companyName, double value, long ammount) {
        this.companyName = companyName;
        this.value = value;
        this.ammount = ammount;
    }
    
    public String getCompanyName() {
        return companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public double getValue() {
        return value;
    }
    
    public void setValue(double value) {
        this.value = value;
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
        result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
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
        Stock other = (Stock) obj;
        if (companyName == null) {
            if (other.companyName != null)
                return false;
        } else if (!companyName.equals(other.companyName))
            return false;
        return true;
    }
    
}
