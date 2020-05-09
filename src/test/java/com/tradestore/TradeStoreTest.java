package com.tradestore;

import com.tradestore.exceptions.InvalidTradeException;
import com.tradestore.model.Trade;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class TradeStoreTest {

    private TradeServiceImpl tradestoreTarget;

    @Before
    public void initTarget(){
        tradestoreTarget = new TradeServiceImpl();
    }

    @Test(expected = InvalidTradeException.class)
    public void onRecieveInvalidNullTradeThrowException() throws InvalidTradeException {
        Trade tradeObject = null;
        tradestoreTarget.recieveTrade(tradeObject);
    }

    @Test
    public void onReciveAValidTradeGetsValidated(){
        //TODO : Read from some json list of trades
        Trade currentTradeRec = new Trade.Builder()
                .setTradeId("T1")
                .setBookId("B-1")
                .setCounterPartyId("CP-1")
                .setCreatedDate( LocalDateTime.now())
                .setExpired(false)
                .setMaturityDate( LocalDateTime.now())
                .setVersion(1)
                .build();
        try {
            tradestoreTarget.recieveTrade(currentTradeRec);
        } catch (InvalidTradeException e) {
            e.printStackTrace();
        }

    }
}
