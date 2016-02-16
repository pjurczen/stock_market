package stockMarket.data.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import stockMarket.components.StockMarketSimulator;
import stockMarket.data.StockData;
import stockMarket.entity.DataEntity;
import stockMarket.entity.StockEntity;
import stockMarket.mapper.DateMapper;
import stockMarket.mapper.StockMapper;
import stockMarket.repository.StockRepository;
import stockMarket.to.DataTo;
import stockMarket.to.StockTo;

@Service("stockData")
public class StockDataImpl implements StockData {

    private StockMarketSimulator stockMarketSimulator;
    
    private StockRepository stockRepository;
    
    @Autowired
    public StockDataImpl(StockMarketSimulator stockMarketSimulator, StockRepository stockRepository) {
        this.stockMarketSimulator = stockMarketSimulator;
        this.stockRepository = stockRepository;
    }
    
    @Override
    @Transactional(readOnly = true)
    public DataTo getStockPrices() {
        DataEntity data = null;
        data = stockRepository.findByDate(stockMarketSimulator.getDate().toString());
        while(data == null) {
            stockMarketSimulator.skipDate();
            data = stockRepository.findByDate(stockMarketSimulator.getDate().toString());
        }
        return new DataTo(StockMapper.map2To(data.getStocks()), DateMapper.map2To(data));
    }

    @Override
    @Transactional(readOnly = false)
    public void saveStockData(Map<LocalDate, Set<StockTo>> stocks) {
        for(Map.Entry<LocalDate, Set<StockTo>> entry : stocks.entrySet()) {
            DataEntity date = DateMapper.map(entry.getKey());
            List<StockEntity> stocksSet = StockMapper.map2Entity(entry.getValue());
            date.setStocks(stocksSet);
            stockRepository.save(date);
        }
    }

}
