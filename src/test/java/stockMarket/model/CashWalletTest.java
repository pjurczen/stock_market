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
        CashTo cash = new CashTo("PLN", 1, 500);
        //when
        long expectedAmmount = cashWallet.getBalanceInPLN().getAmmount() + 500;
        cashWallet.deposit(cash);
        //then
        assertNotNull(cashWallet.getBalance());
        assertEquals(expectedAmmount, cashWallet.getBalanceInPLN().getAmmount());
    }
    
    @Test
    @DirtiesContext
    public void shouldSumCashWhenExisted() {
        //given
        CashTo cash = new CashTo("PLN", 1, 500);
        //when
        long expectedAmmount = cashWallet.getBalanceInPLN().getAmmount() + 1000;
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
        CashTo cash = new CashTo("PLN", 1, 500);
        CashTo cashToWithdraw = new CashTo("PLN", 1, 300);
        //when
        long expectedWithdrawn = cashToWithdraw.getAmmount();
        long expectedBalance = cashWallet.getBalanceInPLN().getAmmount() + cash.getAmmount() - cashToWithdraw.getAmmount();
        cashWallet.deposit(cash);
        CashTo cashFromWallet = cashWallet.withdraw(cashToWithdraw);
        //then
        assertNotNull(cashWallet.getBalance());
        assertEquals(expectedBalance, cashWallet.getBalanceInPLN().getAmmount());
        assertEquals(expectedWithdrawn, cashFromWallet.getAmmount());
    }
    
    @Test
    @DirtiesContext
    public void shouldOpenDay() {
        //given
        CashTo plns = new CashTo("PLN", 1, 500);
        CashTo euros = new CashTo("EUR", 4.2, 300);
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
        CashTo plns = new CashTo("PLN", 1, 500);
        CashTo euros = new CashTo("EUR", 4.2, 300);
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
