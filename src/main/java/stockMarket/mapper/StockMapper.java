package stockMarket.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import stockMarket.entity.StockEntity;
import stockMarket.to.StockTo;

public class StockMapper {
    
    public static List<StockEntity> map2Entity(Set<StockTo> stocks) {
        return stocks.stream().map(StockMapper::map).collect(Collectors.toList());
    }
    
    public static Set<StockTo> map2To(List<StockEntity> stocks) {
        return stocks.stream().map(StockMapper::map).collect(Collectors.toSet());
    }
    
    public static StockEntity map(StockTo stockTo) {
        if(stockTo != null) {
            return new StockEntity(stockTo.getCompanyName(), stockTo.getValue());
        }
        return null;
    }
    
    public static StockTo map(StockEntity stockEntity) {
        if(stockEntity != null) {
            return new StockTo(stockEntity.getCompanyName(), stockEntity.getValue(), 0);
        }
        return null;
    }
    
}
