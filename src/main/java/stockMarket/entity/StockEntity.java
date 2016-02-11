package stockMarket.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "STOCK")
public class StockEntity implements Serializable {
    
    private static final long serialVersionUID = 7741437536352757152L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false, length = 50)
    private String companyName;
    
    @Column(nullable = false)
    private double value;
    
    //for hibernate
    protected StockEntity() {
    }
    
    public StockEntity(String companyName, double value) {
        this.companyName = companyName;
        this.value = value;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
}
