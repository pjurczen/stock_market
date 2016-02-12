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
import stockMarket.exceptions.NoEndDateSpecifiedException;

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
        LocalDate date = new LocalDate(2016, 2, 10);
        stockMarketSimulator.setStartDate(date);
        stockMarketSimulator.setEndDate(date.plusDays(2));
        stockMarketSimulator.register(client);
        //when
        try {
            stockMarketSimulator.nextDay();
        } catch (NoEndDateSpecifiedException e) {
            e.printStackTrace();
        }
        //then
        Mockito.verify(client, Mockito.times(1)).update();
    }
}
