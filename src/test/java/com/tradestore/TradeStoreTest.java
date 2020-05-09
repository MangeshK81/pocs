package com.tradestore;

import com.tradestore.exceptions.InvalidTradeException;
import com.tradestore.model.Trade;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TradeStoreTest {

    private TradeServiceImpl tradestoreTarget;

    @Before
    public void initTarget() {
        tradestoreTarget = new TradeServiceImpl();
    }

    @Test(expected = InvalidTradeException.class)
    public void onReceiveInvalidNullTradeThrowException() throws InvalidTradeException {
        Trade tradeObject = null;
        tradestoreTarget.recieveTrade(tradeObject);
    }

    @Test
    public void onReceiveAValidTradeGetsValidated() {
        //TODO : Read from some json list of trades
        Trade currentTradeRec = new Trade.Builder()
                .setTradeId("T1")
                .setBookId("B-1")
                .setCounterPartyId("CP-1")
                .setCreatedDate(LocalDateTime.now())
                .setExpired(false)
                .setMaturityDate(LocalDateTime.now())
                .setVersion(1)
                .build();
        try {
            tradestoreTarget.recieveTrade(currentTradeRec);
        } catch (InvalidTradeException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void onReceiveAValidTradeGetsValidatedAndStoredIntoStore() {
        //TODO : Read from some json list of trades
        Trade currentTradeRec = new Trade.Builder()
                .setTradeId("T1")
                .setBookId("B-1")
                .setCounterPartyId("CP-1")
                .setCreatedDate(LocalDateTime.now())
                .setExpired(false)
                .setMaturityDate(LocalDateTime.now())
                .setVersion(1)
                .build();
        try {
            tradestoreTarget.recieveTrade(currentTradeRec);
        } catch (InvalidTradeException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void onReceiveAValidTradeGetsValidatedAndStoredIntoStoreAndReturns() {
        //TODO : Read from some json list of trades
        Trade currentTradeRec = new Trade.Builder()
                .setTradeId("T1")
                .setBookId("B-1")
                .setCounterPartyId("CP-1")
                .setCreatedDate(LocalDateTime.now())
                .setExpired(false)
                .setMaturityDate(LocalDateTime.now())
                .setVersion(1)
                .build();
        try {
            tradestoreTarget.recieveTrade(currentTradeRec);
            Optional<Trade> storedTrade = tradestoreTarget.getTrade(currentTradeRec.getTradeId());
            assertTrue(storedTrade.isPresent());
            assertEquals(storedTrade.get().getTradeId(), currentTradeRec.getTradeId());
        } catch (InvalidTradeException e) {
            e.printStackTrace();
        }

    }

    @Test(expected = InvalidTradeException.class)
    public void onReceiveATradeWithLesserMaturityDateGetsException() throws InvalidTradeException {
        Trade currentTradeRec = new Trade.Builder()
                .setTradeId("T1")
                .setBookId("B-1")
                .setCounterPartyId("CP-1")
                .setCreatedDate(LocalDateTime.now())
                .setExpired(false)
                .setMaturityDate(LocalDateTime.now())
                .setVersion(1)
                .build();

        Trade tradeRecWithLessMaturity = new Trade.Builder()
                .setTradeId("T1")
                .setBookId("B-1")
                .setCounterPartyId("CP-1")
                .setCreatedDate(getPastLocalDateTime())
                .setExpired(false)
                .setMaturityDate(LocalDateTime.now())
                .setVersion(1)
                .build();

            tradestoreTarget.recieveTrade(currentTradeRec);
            tradestoreTarget.recieveTrade(tradeRecWithLessMaturity);

    }

    private LocalDateTime getPastLocalDateTime() {
        return LocalDateTime.of(
                LocalDate.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth() - 1, LocalDateTime.now().getHour(), LocalDateTime.now().getMinute()
        );
    }

}
