package stockMarket.components;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockMarket.exceptions.NoEndDateSpecifiedException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonComponentsTest-context.xml")
public class StockMarketSimulatorTest {

    @Autowired
    private StockMarketSimulator stockMarketSimulator;
    
    @Test
    public void shouldIncrementDay() {
        //given
        LocalDate startDate = new LocalDate(2016, 2, 11);
        LocalDate endDate = new LocalDate(2016, 2, 12);
        stockMarketSimulator.setStartDate(startDate);
        stockMarketSimulator.setEndDate(endDate);
        //when
        try {
            stockMarketSimulator.nextDay();
        } catch (NoEndDateSpecifiedException e) {
            e.printStackTrace();
        }
        int actualDay = stockMarketSimulator.getDate().getDayOfYear();
        //then
        assertEquals(startDate.getDayOfYear() + 1, actualDay);
    }
}
