package com.tradestore;

import com.tradestore.exceptions.InvalidTradeException;
import com.tradestore.model.Trade;

public interface TradeStoreService {
    /*
    *
    * */
    public void recieveTrade(Trade tradeObject) throws InvalidTradeException;
}
