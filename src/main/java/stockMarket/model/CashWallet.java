package stockMarket.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class CashWallet {

    private Map<Cash, Long> account;
    private double euroSellingRate;
    private double euroBuyingRate;
    
    public CashWallet() {
        this.account = new HashMap<Cash, Long>();
    }
    
    public void closeDay() {
        computeBalanceToHalfInEuro();
    }
    
    public void openDay() {
        setRates();
        computeBalanceToPLN();
    }
    
    public Cash getBalanceInPLN() {
        return account.keySet()
                        .stream()
                        .filter(x -> x.equals(new Cash("PLN", 1, 0)))
                        .findFirst()
                        .get();
    }
    
    public Set<Cash> getBalance() {
        return account.keySet();
    }

    public Cash withdraw(Cash cash) {
        if(getBalanceInPLN().getAmmount() >= cash.getAmmount()) {
            long balanceLeft = getBalanceInPLN().getAmmount() - cash.getAmmount();
            Cash cashLeft = new Cash("PLN", 1, balanceLeft);
            account.remove(cash);
            account.put(cashLeft, balanceLeft);
            return cash;
        }
        return null;
    }
    
    public void deposit(Cash cash) {
        if(account.containsKey(cash)) {
            long ammountToDeposit = account.get(cash) + cash.getAmmount();
            Cash cashToDeposit = new Cash(cash.getCurrency(), cash.getRatio(), ammountToDeposit);
            account.remove(cash);
            account.put(cashToDeposit, ammountToDeposit);
        } else {
            account.put(cash, cash.getAmmount());
        }
    }

    private void setRates() {
        euroBuyingRate = 1.02*(3.9 + 0.2*Math.random());
        euroSellingRate = 0.98*(3.9 + 0.2*Math.random());
        if(account.containsKey(new Cash("EUR", 0, 0))) {
            Cash tmpCash = account.keySet()
                                    .stream()
                                    .filter(x -> x.equals(new Cash("EUR", 1, 0)))
                                    .findFirst()
                                    .get();
            account.remove(tmpCash);
            account.put(new Cash("EUR", euroSellingRate, tmpCash.getAmmount()), tmpCash.getAmmount());
        }
    }
    
    private void computeBalanceToPLN() {
        long equalityInPLN = 0;
        for(Map.Entry<Cash, Long> entry : account.entrySet()) {
            equalityInPLN += entry.getValue() * entry.getKey().getRatio();
        }
        account.clear();
        account.put(new Cash("PLN", 1, equalityInPLN), equalityInPLN);
    }
    
    private void computeBalanceToHalfInEuro() {
        Cash tmpCash = getBalanceInPLN();
        account.clear();
        long euroToDeposit = (long)(0.5*tmpCash.getAmmount()/euroBuyingRate);
        account.put(new Cash("EUR", euroSellingRate, euroToDeposit), euroToDeposit);
        long plnsLeft = tmpCash.getAmmount() - (long)(euroToDeposit*euroBuyingRate);
        account.put(new Cash("PLN", 1, plnsLeft), plnsLeft);
    }
}
