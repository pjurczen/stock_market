package stockMarket.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonModelTest-context.xml")
public class StockWalletTest {

    @Autowired
    StockWallet stockWallet;
    
    @Test
    @DirtiesContext
    public void shouldAddStocks() {
        //given
        StockTo stock = new StockTo("INTEL", 24.1, 500);
        //when
        long expectedAmmount = 500;
        stockWallet.addStocks(stock);
        //then
        assertNotNull(stockWallet.getStocks());
        assertEquals(expectedAmmount, stockWallet.getStocks()
                                                    .stream()
                                                    .filter(x-> x.equals(stock))
                                                    .findFirst()
                                                    .get()
                                                    .getAmmount());
    }
    
    @Test
    @DirtiesContext
    public void shouldSumStocksWhenExisted() {
        //given
        StockTo stock = new StockTo("INTEL", 24.1, 500);
        //when
        long expectedAmmount = 1000;
        stockWallet.addStocks(stock);
        stockWallet.addStocks(stock);
        //then
        assertNotNull(stockWallet.getStocks());
        assertEquals(expectedAmmount, stockWallet.getStocks()
                                                    .stream()
                                                    .filter(x-> x.equals(stock))
                                                    .findFirst()
                                                    .get()
                                                    .getAmmount());
    }
    
    @Test
    public void shouldRemoveStocksWhenExisted() {
        //given
        StockTo stock = new StockTo("INTEL", 24.1, 500);
        //when
        stockWallet.addStocks(stock);
        stockWallet.removeStocks(stock);
        //then
        assertFalse(stockWallet.getStocks().contains(stock));
    }
}
