package stockMarket.model;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonModelTest-context.xml")
public class RandomStrategyTest {

    @Autowired
    private Strategy strategy;
    
    Set<Stock> stocks;
    Set<Stock> clientStocks;
    
    @Before
    public void prepare() {
        stocks = new HashSet<Stock>();
        clientStocks = new HashSet<Stock>();
        Stock intelStocks = new Stock("INTEL", 21.3, 10);
        Stock hpStocks = new Stock("HP", 11.2, 15);
        stocks.add(intelStocks);
        stocks.add(hpStocks);
        clientStocks.add(hpStocks);
    }
    
    @Test
    public void shouldChooseStockToBuy() {
        //given
        //when
        Stock stocksToBuy = strategy.chooseStockToBuy(stocks);
        //then
        assertNotNull(stocksToBuy);
    }
    
    @Test
    public void shouldChooseStockToSell() {
        //given
        //when
        Stock stocksToSell = strategy.chooseStockToSell(stocks, clientStocks);
        //then
        assertNotNull(stocksToSell);
    }
}
