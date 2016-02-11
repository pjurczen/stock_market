package stockMarket.data.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import stockMarket.components.StockMarketSimulator;
import stockMarket.data.StockData;
import stockMarket.mapper.StockMapper;
import stockMarket.model.StockTo;
import stockMarket.repository.StockRepository;

@Service("stockData")
public class StockDataImpl implements StockData {

    private StockMarketSimulator stockMarketSimulator;
    
    @Autowired
    private StockRepository stockRepository;
    
    @Autowired
    public StockDataImpl(StockMarketSimulator stockMarketSimulator) {
        this.stockMarketSimulator = stockMarketSimulator;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Set<StockTo> getStockPrices() {
        return StockMapper.map2To(stockRepository.findByDate(stockMarketSimulator.getDate().toString()).getStocks());
    }

    @Override
    @Transactional(readOnly = false)
    public void saveStockData() {
    }

}
