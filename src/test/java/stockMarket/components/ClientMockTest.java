package stockMarket.components;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;

import stockMarket.components.impl.ClientImpl;
import stockMarket.components.impl.StockBrokerImpl;
import stockMarket.components.impl.StockMarketSimulatorImpl;
import stockMarket.model.CashTo;
import stockMarket.model.CashWallet;
import stockMarket.model.StockTo;
import stockMarket.model.StockWallet;
import stockMarket.strategy.impl.RandomStrategy;

public class ClientMockTest {
    
    @InjectMocks
    private ClientImpl client;
    
    @Mock
    private CashWallet cashWallet;
    @Mock
    private StockWallet stockWallet;
    @Mock
    private StockBrokerImpl stockBroker;
    @Mock
    private RandomStrategy strategy;
    
    private StockMarketSimulatorImpl stockMarketSimulator;
    private Set<StockTo> stocksInMarket;
    private CashTo myCash;
    private Set<StockTo> myStocks;
    
    @Before
    public void setUp() {
        myCash = new CashTo("PLN", 1, 10000);
        stocksInMarket = new HashSet<StockTo>();
        stocksInMarket.add(new StockTo("Apple", 102.4, 1));
        stocksInMarket.add(new StockTo("Windows", 11.1, 1));
        stocksInMarket.add(new StockTo("HP", 33.3, 1));
        myStocks = new HashSet<StockTo>();
        myStocks.add(new StockTo("Apple", 102.4, 1));
        myStocks.add(new StockTo("Windows", 11.1, 1));
        myStocks.add(new StockTo("HP", 33.3, 1));
        stockMarketSimulator = new StockMarketSimulatorImpl();
        MockitoAnnotations.initMocks(this);
        Whitebox.setInternalState(client, "stockMarketSimulator", stockMarketSimulator);
    }
    
    @Test
    public void shouldNotBuyStocks() {
        //given
        Mockito.when(stockBroker.getStockPrices()).thenReturn(stocksInMarket);
        Mockito.when(cashWallet.getBalanceInPLN()).thenReturn(myCash);
        Mockito.when(strategy.chooseStockToBuy(stocksInMarket, myCash)).thenReturn(new StockTo(null, 102.4, 15));
        this.client.setUp();
        //when
        client.buy();
        //then
        Mockito.verify(cashWallet, Mockito.times(1)).getBalanceInPLN();
        Mockito.verify(strategy, Mockito.times(1)).chooseStockToBuy(stocksInMarket, myCash);
    }
    
    @Test
    public void shouldNotSellStocks() {
        //given
        Mockito.when(stockBroker.getStockPrices()).thenReturn(stocksInMarket);
        Mockito.when(stockWallet.getStocks()).thenReturn(myStocks);
        Mockito.when(strategy.chooseStockToSell(stocksInMarket, myStocks)).thenReturn(new StockTo(null, 102.4, 15));
        this.client.setUp();
        //when
        client.sell();
        //then
        Mockito.verify(stockWallet, Mockito.times(1)).getStocks();
        Mockito.verify(strategy, Mockito.times(1)).chooseStockToSell(stocksInMarket, myStocks);
    }
}
