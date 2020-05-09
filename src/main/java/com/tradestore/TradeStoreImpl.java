package com.tradestore;


import com.tradestore.exceptions.InvalidTradeException;
import com.tradestore.model.Trade;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class will be actual Trade store ( cache ) implementation
 * Trade Id	Version	Counter-Party Id	Book-Id	Maturity Date	Created Date	Expired
 * T1	            1	CP-1	            B1	    20/05/2020	    <today date>	N
 * T2	            2	CP-2	            B1	    20/05/2021	    <today date>	N
 * T2	            1	CP-1	            B1	    20/05/2021	    14/03/2015	N
 * T3	            3	CP-3	            B2	    20/05/2014	    <today date>	Y
 * <p>
 * There are couples of validation, we need to provide in the above assignment
 * 1.	During transmission if the lower version is being received by the store it will reject the trade and throw an
 * exception. If the version is same it will override the existing record.
 * 2.	Store should not allow the trade which has less maturity date then today date.
 * 3.	Store should automatically update the expire flag if in a store the trade crosses the maturity date.
 * This class will have above validations
 * <p>
 * ASSUMPTION : TRADE WILL HAVE ATLEAST ONE UNIQUE IDENTIFIER ; ASSUMING TRADE ID IS UNIQUE ID
 */
public class TradeStoreImpl implements TradeStore {

    private final Map<String, SortedSet<Trade>> map;

    private static final TradeStoreImpl store = new TradeStoreImpl();

    private static final Comparator<Trade> TRADE_VERSION_COMPARATOR = Comparator.comparing(Trade::getVersion);

    public static TradeStoreImpl getInstance() {
        return store;
    }

    private TradeStoreImpl() {
        map = new ConcurrentHashMap<>();
    }

    @Override
    public void put(Object key, Object value) throws InvalidTradeException {
        if (map.containsKey(key)) {
            // check if it has lower version trade
            Trade newbieTrade = (Trade) value;
            SortedSet<Trade> existingTrades = map.get(key);
            if (shouldDiscardNewTrade(newbieTrade, existingTrades) || hasNewTradeLesserMaturityDateThanToday(newbieTrade)) {
                throw new InvalidTradeException("Found invalid trade : lower version found");
            }
            existingTrades.add(newbieTrade);
            map.put((String) key, existingTrades);

        } else {
            SortedSet<Trade> versionsOfTrade = new TreeSet<>();
            versionsOfTrade.add((Trade) value);
            map.put((String) key, versionsOfTrade);

        }


    }

    private boolean hasNewTradeLesserMaturityDateThanToday(Trade newbieTrade) {
        return newbieTrade.getMaturityDate().isBefore(LocalDateTime.now());
    }

    private boolean shouldDiscardNewTrade(Trade newbieTrade, SortedSet<Trade> existingTrades) {
        TreeSet<Trade> orderedTrades = getTradesInOrder(existingTrades);
        Iterator<Trade> iterator = orderedTrades.descendingIterator();
        int counter = 0;
        while (iterator.hasNext() && counter <= 1) {
            counter++;
            Trade highestVersionTrade = iterator.next();
            if (highestVersionTrade.getVersion() > newbieTrade.getVersion())
                return true;
        }
        return false;
    }

    private TreeSet<Trade> getTradesInOrder(SortedSet<Trade> existingTrades) {
        TreeSet<Trade> orderedTrades = new TreeSet<>(TRADE_VERSION_COMPARATOR);
        orderedTrades.addAll(existingTrades);
        return orderedTrades;
    }

    @Override
    public Trade get(Object key) {
        return map.get(key).last();
    }

}
