package com.tradestore;

import com.tradestore.exceptions.InvalidTradeException;

public interface TradeStore<K, V> {
    void put(K key, V value) throws InvalidTradeException;

    V get(K key);
}
