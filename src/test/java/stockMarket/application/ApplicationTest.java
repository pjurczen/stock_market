package stockMarket.application;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockMarket.components.StockMarketSimulator;
import stockMarket.components.impl.ClientImpl;
import stockMarket.data.StockData;
import stockMarket.dataLoader.DataLoader;
import stockMarket.strategy.Strategy;
import stockMarket.to.CashTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonApplicationTest-context.xml")
public class ApplicationTest {
    
    @Autowired
    private StockMarketSimulator stockMarketSimulator;
    @Autowired
    private StockData stockData;
    @Autowired
    private DataLoader dataLoader;
    @Autowired
    private ClientImpl client;
    @Autowired
    private Strategy sellFallingBuyRisingStrategy;
    
    private LocalDate startDate;
    private LocalDate endDate;
    private static final String csvPath = "/dane.csv";
    
    @Before
    public void setUp() {
        startDate = new LocalDate(2013, 1, 2);
        endDate = new LocalDate(2013, 12, 30);
        stockMarketSimulator.setStartDate(startDate);
        stockMarketSimulator.setEndDate(endDate);
        stockData.saveStockData(dataLoader.loadStocksFromFile(csvPath));
        stockMarketSimulator.register(client);
    }
    
    @Test
    @DirtiesContext
    public void shouldSimulateMarketWithRandomStrategy() {
        //given
        //when
        stockMarketSimulator.notifyAllObservers();
        CashTo cashAfterSimulation = client.sumUpCash();
        long totalCash = cashAfterSimulation.getAmmount();
        //then
        assertTrue(totalCash > 0);
        assertNotNull(cashAfterSimulation);
        System.out.println("Finished with total of " + totalCash + cashAfterSimulation.getCurrency());
    }
    
    @Test
    @DirtiesContext
    public void shouldSimulateMarketWithSellFallingBuyRisingStrategy() {
        //given
        client.setStrategy(sellFallingBuyRisingStrategy);
        //when
        stockMarketSimulator.notifyAllObservers();
        CashTo cashAfterSimulation = client.sumUpCash();
        long totalCash = cashAfterSimulation.getAmmount();
        //then
        assertTrue(totalCash > 0);
        assertNotNull(cashAfterSimulation);
        System.out.println("Finished with total of " + totalCash + cashAfterSimulation.getCurrency());
    }
}
