package stockMarket.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonModelTest-context.xml")
public class CashWalletTest {

    @Autowired
    CashWallet cashWallet;
    
    @Test
    @DirtiesContext
    public void shouldAddCash() {
        //given
        Cash cash = new Cash("PLN", 1, 500);
        //when
        long expectedAmmount = 500;
        cashWallet.deposit(cash);
        //then
        assertNotNull(cashWallet.getBalance());
        assertEquals(expectedAmmount, cashWallet.getBalance().getAmmount());
    }
    
    @Test
    @DirtiesContext
    public void shouldSumCashWhenExisted() {
        //given
        Cash cash = new Cash("PLN", 1, 500);
        //when
        long expectedAmmount = 1000;
        cashWallet.deposit(cash);
        cashWallet.deposit(cash);
        //then
        assertNotNull(cashWallet.getBalance());
        assertEquals(expectedAmmount, cashWallet.getBalance().getAmmount());
    }
    
}
