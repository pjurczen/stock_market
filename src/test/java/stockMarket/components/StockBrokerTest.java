package stockMarket.components;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockMarket.model.CashTo;
import stockMarket.model.StockTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonComponentsTest-context.xml")
public class StockBrokerTest {
    
    @Autowired
    private StockBroker stockBroker;
    
    private StockTo testStock;
    
    @Before
    public void setUp() {
        testStock = new StockTo("Apple", 14.1, 243);
    }
    
    @Test
    public void shouldReturnRandomBuyingOffer() {
        //given
        //when
        StockTo randomOffer = stockBroker.getBuyingOffer(testStock);
        StockTo anotherRandomOffer = stockBroker.getBuyingOffer(testStock);
        //then
        assertEquals(randomOffer, testStock);
        assertEquals(anotherRandomOffer, testStock);
        assertNotEquals(randomOffer.getAmmount(), anotherRandomOffer.getAmmount());
        assertNotEquals(randomOffer.getValue(), anotherRandomOffer.getValue());
    }
    
    @Test
    public void shouldReturnRandomSellingOffer() {
        //given
        //when
        StockTo randomOffer = stockBroker.getSellingOffer(testStock);
        StockTo anotherRandomOffer = stockBroker.getSellingOffer(testStock);
        //then
        assertEquals(randomOffer, testStock);
        assertEquals(anotherRandomOffer, testStock);
        assertNotEquals(randomOffer.getAmmount(), anotherRandomOffer.getAmmount());
        assertNotEquals(randomOffer.getValue(), anotherRandomOffer.getValue());
    }
    
    @Test
    public void shouldReturnCashForSoldStock() {
        //given
        //when
        StockTo randomOffer = stockBroker.getSellingOffer(testStock);
        CashTo cashForSoldStock = stockBroker.sellStock();
        long expectedCash = (long)(randomOffer.getAmmount()*randomOffer.getValue());
        long actualCash = cashForSoldStock.getAmmount();
        //then
        assertEquals(expectedCash, actualCash);
    }
    
    @Test
    public void shouldReturnCashForBoughtStock() {
        //given
        //when
        StockTo randomOffer = stockBroker.getBuyingOffer(testStock);
        CashTo cashForSoldStock = stockBroker.buyStock();
        long expectedCash = (long)(randomOffer.getAmmount()*randomOffer.getValue());
        long actualCash = cashForSoldStock.getAmmount();
        //then
        assertEquals(expectedCash, actualCash);
    }
}
