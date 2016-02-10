package stockMarket.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CashWallet {

    private long equalityInPLN = 0;
    private Map<Cash, Long> account;
    
    public CashWallet() {
        this.account = new HashMap<Cash, Long>();
    }
    
    public Cash getBalance() {
        equalityInPLN = 0;
        for(Map.Entry<Cash, Long> entry : account.entrySet()) {
            equalityInPLN += entry.getValue() * entry.getKey().getRatio();
        }
        return new Cash("PLN", 1, equalityInPLN);
    }

    public Cash withdraw(long ammount) {
        // TODO Auto-generated method stub
        return null;
    }

    public void deposit(Cash cash) {
        if(account.containsKey(cash)) {
            account.replace(cash, account.get(cash) + cash.getAmmount());
        } else {
            account.put(cash, cash.getAmmount());
        }
    }
}
