package stockMarket.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "DATA")
public class DataEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private static final long serialVersionUID = -4308712094031592655L;
    
    @Column(nullable = false, length = 50)
    private String date;
    
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<StockEntity> stocks = new ArrayList<StockEntity>();
    
    // for hibernate
    protected DataEntity() {
    }
    
    public DataEntity(String date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public List<StockEntity> getStocks() {
        return stocks;
    }
    
    public void setStocks(List<StockEntity> stocks) {
        this.stocks = stocks;
    }
}
