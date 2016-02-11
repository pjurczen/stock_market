package stockMarket.components;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import stockMarket.components.impl.ClientImpl;
import stockMarket.components.impl.StockMarketSimulatorImpl;

public class StockMarketSimulatorMockTest {

    @Mock
    private ClientImpl client;
    
    @InjectMocks
    private StockMarketSimulatorImpl stockMarketSimulator;
    
    @Before
    public void prepare() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void shouldCallClientUpdate() {
        //given
        LocalDate date = new LocalDate();
        stockMarketSimulator.setStartDay(date);
        stockMarketSimulator.setEndDay(date.plusDays(2));
        stockMarketSimulator.register(client);
        //when
        stockMarketSimulator.nextDay();
        //then
        Mockito.verify(client, Mockito.times(1)).update();
    }
}
