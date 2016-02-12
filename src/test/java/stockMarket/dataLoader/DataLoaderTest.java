package stockMarket.dataLoader;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Set;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockMarket.dataLoader.DataLoader;
import stockMarket.to.StockTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonDataLoaderTest-context.xml")
public class DataLoaderTest {
    
    @Autowired
    private DataLoader dataLoader;
    
    private static final String csvPath = "/dane.csv";
    
    @Test
    public void shouldParseData() {
        //given
        //when
        Map<LocalDate, Set<StockTo>> stocks = dataLoader.loadStocksFromFile(csvPath);
        int size = stocks.size();
        //then
        assertNotNull(stocks);
        assertTrue(size > 0);
    }
}
