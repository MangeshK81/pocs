package com.tradestore;

import com.tradestore.exceptions.InvalidTradeException;
import com.tradestore.model.Trade;

import java.util.Optional;

public interface TradeStoreService {
    /*
    *   Recieve the trade and stores into tradestore
    * */
    void recieveTrade(Trade tradeObject) throws InvalidTradeException;

    Optional<Trade> getTrade(String tradeId);
}
