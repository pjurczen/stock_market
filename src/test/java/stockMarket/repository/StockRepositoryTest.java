package stockMarket.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import stockMarket.entity.DataEntity;
import stockMarket.entity.StockEntity;
import stockMarket.mapper.DateMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class StockRepositoryTest {
    
    @Autowired
    private StockRepository stockRepository;
    
    private List<StockEntity> stocksList;
    private LocalDate date;
    private DataEntity dataEntity;
    
    @Before
    public void setUp() {
        date = new LocalDate();
        stocksList = new ArrayList<StockEntity>();
        stocksList.add(new StockEntity("Intel", 1));
        stocksList.add(new StockEntity("Apple", 23));
        dataEntity = DateMapper.map(date);
        dataEntity.setStocks(stocksList);
    }
    
    @Test
    @Transactional
    @DirtiesContext
    public void shouldFindStocksByData() {
        //given
        stockRepository.save(dataEntity);
        int expectedStocksSize = stocksList.size();
        //when
        DataEntity dateEnityFromDB = stockRepository.findByDate(date.toString());
        List<StockEntity> stocks = dateEnityFromDB.getStocks();
        //then
        assertNotNull(stocks);
        assertEquals(expectedStocksSize, stocks.size());
        assertEquals(dataEntity, dateEnityFromDB);
    }
}
