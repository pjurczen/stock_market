package stockMarket.data;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockMarket.components.StockMarketSimulator;
import stockMarket.to.StockTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonDataTest-context.xml")
public class StockDataTest {

    @Autowired
    private StockData stockData;
    
    @Autowired
    private StockMarketSimulator stockMarketSimulator;
    
    Map<LocalDate, Set<StockTo>> stocks;
    Set<StockTo> stocksSet;
    LocalDate date;
    
    @Before
    public void setUp() {
        date = new LocalDate();
        stocks = new HashMap<LocalDate, Set<StockTo>>();
        stocksSet = new HashSet<StockTo>();
        stocksSet.add(new StockTo("Intel", 1, 1));
        stocksSet.add(new StockTo("Apple", 23, 1));
        stocks.put(date, stocksSet);
    }
    
    @Test
    public void shouldSaveDataToDatabase() {
        //given
        //when
        stockMarketSimulator.setStartDate(date);
        stockData.saveStockData(stocks);
        Set<StockTo> stock = stockData.getStockPrices();
        int expectedStockSize = stocksSet.size();
        //then
        assertNotNull(stock);
        assertEquals(expectedStockSize, stock.size());
    }
}
