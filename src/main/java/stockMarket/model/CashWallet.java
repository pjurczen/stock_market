package stockMarket.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import stockMarket.to.CashTo;

@Component
public class CashWallet {

    private Map<CashTo, Long> account;
    private double euroSellingRate;
    private double euroBuyingRate;
    
    public CashWallet() {
        this.account = new HashMap<CashTo, Long>();
        CashTo startingCash = new CashTo("PLN", 1, 10000);
        account.put(startingCash, startingCash.getAmmount());
    }
    
    public void closeDay() {
        computeBalanceToHalfInEuro();
    }
    
    public void openDay() {
        setRates();
        computeBalanceToPLN();
    }
    
    public CashTo getBalanceInPLN() {
        return account.keySet()
                        .stream()
                        .filter(x -> x.equals(new CashTo("PLN", 1, 0)))
                        .findFirst()
                        .get();
    }
    
    public Set<CashTo> getBalance() {
        return account.keySet();
    }

    public CashTo withdraw(CashTo cash) {
        if(getBalanceInPLN().getAmmount() >= cash.getAmmount()) {
            long balanceLeft = getBalanceInPLN().getAmmount() - cash.getAmmount();
            CashTo cashLeft = new CashTo("PLN", 1, balanceLeft);
            account.remove(cash);
            account.put(cashLeft, balanceLeft);
            return cash;
        }
        return null;
    }
    
    public void deposit(CashTo cash) {
        if(account.containsKey(cash)) {
            long ammountToDeposit = account.get(cash) + cash.getAmmount();
            CashTo cashToDeposit = new CashTo(cash.getCurrency(), cash.getRatio(), ammountToDeposit);
            account.remove(cash);
            account.put(cashToDeposit, ammountToDeposit);
        } else {
            account.put(cash, cash.getAmmount());
        }
    }

    private void setRates() {
        euroBuyingRate = 1.02*(3.9 + 0.2*Math.random());
        euroSellingRate = 0.98*(3.9 + 0.2*Math.random());
        if(account.containsKey(new CashTo("EUR", 0, 0))) {
            CashTo tmpCash = account.keySet()
                                    .stream()
                                    .filter(x -> x.equals(new CashTo("EUR", 1, 0)))
                                    .findFirst()
                                    .get();
            account.remove(tmpCash);
            account.put(new CashTo("EUR", euroSellingRate, tmpCash.getAmmount()), tmpCash.getAmmount());
        }
    }
    
    private void computeBalanceToPLN() {
        long equalityInPLN = 0;
        for(Map.Entry<CashTo, Long> entry : account.entrySet()) {
            equalityInPLN += entry.getValue() * entry.getKey().getRatio();
        }
        account.clear();
        account.put(new CashTo("PLN", 1, equalityInPLN), equalityInPLN);
    }
    
    private void computeBalanceToHalfInEuro() {
        CashTo tmpCash = getBalanceInPLN();
        account.clear();
        long euroToDeposit = (long)(0.5*tmpCash.getAmmount()/euroBuyingRate);
        account.put(new CashTo("EUR", euroSellingRate, euroToDeposit), euroToDeposit);
        long plnsLeft = tmpCash.getAmmount() - (long)(euroToDeposit*euroBuyingRate);
        account.put(new CashTo("PLN", 1, plnsLeft), plnsLeft);
    }
}
