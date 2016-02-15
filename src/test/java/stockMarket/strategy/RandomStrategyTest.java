package stockMarket.strategy;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockMarket.strategy.Strategy;
import stockMarket.to.CashTo;
import stockMarket.to.DataTo;
import stockMarket.to.StockTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonStrategyTest-context.xml")
public class RandomStrategyTest {

    @Autowired
    private Strategy strategy;
    
    private Set<StockTo> stocks;
    private Set<StockTo> clientStocks;
    private DataTo dataTo;
    private CashTo myCash;
    
    @Before
    public void prepare() {
        stocks = new HashSet<StockTo>();
        clientStocks = new HashSet<StockTo>();
        StockTo intelStocks = new StockTo("INTEL", 21.3, 10);
        StockTo hpStocks = new StockTo("HP", 11.2, 15);
        StockTo appleStocks = new StockTo("Apple", 100.2, 16);
        stocks.add(intelStocks);
        stocks.add(hpStocks);
        stocks.add(appleStocks);
        clientStocks.add(hpStocks);
        clientStocks.add(appleStocks);
        clientStocks.add(intelStocks);
        myCash = new CashTo("PLN", 1, 500);
        dataTo = new DataTo(stocks, new LocalDate());
    }
    
    @Test
    public void shouldChooseStockToBuy() {
        //given
        //when
        Set<StockTo> stocksToBuy = strategy.chooseStocksToBuy(dataTo, myCash);
        //then
        assertNotNull(stocksToBuy);
    }
    
    @Test
    public void shouldChooseStockToSell() {
        //given
        //when
        Set<StockTo> stocksToSell = strategy.chooseStocksToSell(dataTo, clientStocks);
        //then
        assertNotNull(stocksToSell);
    }
}
