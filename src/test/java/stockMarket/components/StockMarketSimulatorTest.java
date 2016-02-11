package stockMarket.components;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonComponentsTest-context.xml")
public class StockMarketSimulatorTest {

    @Autowired
    private StockMarketSimulator stockMarketSimulator;
    
    @Test
    public void shouldIncrementDay() {
        //given
        LocalDate startDate = new LocalDate();
        stockMarketSimulator.setStartDay(startDate);
        //when
        stockMarketSimulator.nextDay();
        int actualDay = stockMarketSimulator.getDate().getDayOfYear();
        //then
        assertEquals(startDate.getDayOfYear() + 1, actualDay);
    }
}
