package stockMarket.components;

import stockMarket.strategy.Strategy;

public interface Client {
    void buy();
    void sell();
    void setStrategy(Strategy strategy);
}
