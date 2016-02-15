package stockMarket.components;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;
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
import stockMarket.model.CashWallet;
import stockMarket.model.StockWallet;
import stockMarket.strategy.impl.RandomStrategy;
import stockMarket.to.CashTo;
import stockMarket.to.DataTo;
import stockMarket.to.StockTo;

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
    private DataTo dataTo;
    
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
        dataTo = new DataTo(stocksInMarket, new LocalDate());
        MockitoAnnotations.initMocks(this);
        Whitebox.setInternalState(client, "stockMarketSimulator", stockMarketSimulator);
    }
    
    @Test
    public void shouldNotBuyStocks() {
        //given
        Mockito.when(stockBroker.getStockPrices()).thenReturn(dataTo);
        Mockito.when(cashWallet.getBalanceInPLN()).thenReturn(myCash);
        Mockito.when(strategy.chooseStocksToBuy(dataTo, myCash)).thenReturn(new HashSet<StockTo>(Arrays.asList(new StockTo(null, 102.4, 15))));
        //when
        client.buy();
        //then
        Mockito.verify(cashWallet, Mockito.times(1)).getBalanceInPLN();
        Mockito.verify(strategy, Mockito.times(1)).chooseStocksToBuy(dataTo, myCash);
    }
    
    @Test
    public void shouldNotSellStocks() {
        //given
        Mockito.when(stockBroker.getStockPrices()).thenReturn(dataTo);
        Mockito.when(stockWallet.getStocks()).thenReturn(myStocks);
        Mockito.when(strategy.chooseStocksToSell(dataTo, myStocks)).thenReturn(new HashSet<StockTo>(Arrays.asList(new StockTo(null, 102.4, 15))));
        //when
        //when
        client.sell();
        //then
        Mockito.verify(stockWallet, Mockito.times(1)).getStocks();
        Mockito.verify(strategy, Mockito.times(1)).chooseStocksToSell(dataTo, myStocks);
    }
}
