package com.tradestore;

import com.tradestore.exceptions.InvalidTradeException;
import com.tradestore.model.Trade;
import org.junit.Before;
import org.junit.Test;

public class TradeStoreTest {

    private TradeServiceImpl tradestoreTarget;

    @Before
    public void initTarget(){
        tradestoreTarget = new TradeServiceImpl();
    }

    @Test(expected = InvalidTradeException.class)
    public void onRecieveOfTradeValidateItsNotNull(){

        Trade tradeObject = null;

        tradestoreTarget.recieveTrade(tradeObject);
    }
}
