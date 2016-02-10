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
        assertEquals(expectedAmmount, cashWallet.getBalanceInPLN().getAmmount());
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
        assertEquals(expectedAmmount, cashWallet.getBalanceInPLN().getAmmount());
    }
    
    @Test
    @DirtiesContext
    public void shouldWithdrawCash() {
        //given
        Cash cash = new Cash("PLN", 1, 500);
        Cash cashToWithdraw = new Cash("PLN", 1, 300);
        //when
        long expectedWithdrawn = cashToWithdraw.getAmmount();
        long expectedBalance = cash.getAmmount() - cashToWithdraw.getAmmount();
        cashWallet.deposit(cash);
        Cash cashFromWallet = cashWallet.withdraw(cashToWithdraw);
        //then
        assertNotNull(cashWallet.getBalance());
        assertEquals(expectedBalance, cashWallet.getBalanceInPLN().getAmmount());
        assertEquals(expectedWithdrawn, cashFromWallet.getAmmount());
    }
    
    @Test
    @DirtiesContext
    public void shouldOpenDay() {
        //given
        Cash plns = new Cash("PLN", 1, 500);
        Cash euros = new Cash("EUR", 4.2, 300);
        //when
        cashWallet.deposit(plns);
        cashWallet.deposit(euros);
        cashWallet.openDay();
        long balanceInPLN = cashWallet.getBalanceInPLN().getAmmount();
        //then
        assertNotNull(cashWallet.getBalance());
        assertNotEquals(0, balanceInPLN);
    }
    
    @Test
    @DirtiesContext
    public void shouldCloseDay() {
        //given
        Cash plns = new Cash("PLN", 1, 500);
        Cash euros = new Cash("EUR", 4.2, 300);
        //when
        cashWallet.deposit(plns);
        cashWallet.deposit(euros);
        cashWallet.openDay();
        cashWallet.closeDay();
        //then
        assertNotNull(cashWallet.getBalance());
        assertEquals(2, cashWallet.getBalance().size());
    }
}
