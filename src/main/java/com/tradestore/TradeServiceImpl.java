package com.tradestore;

import com.tradestore.exceptions.InvalidTradeException;
import com.tradestore.model.Trade;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TradeServiceImpl implements TradeStoreService{

    @Override
    public void recieveTrade(Trade trade) throws InvalidTradeException {
        // Validate Trade
        validateTrade(trade);




    }

    private void validateTrade(Trade trade) throws InvalidTradeException {
        // if its null
        if(trade == null) {
            throw new InvalidTradeException("Invalid Trade: Invalid object type  or null");
        }

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Trade>> violations = validator.validate(trade);
        if(violations.size()>0) {
            List<String> messages = violations.stream().map(e -> e.getInvalidValue() + " is invalid value; Trade is Invalid").collect(Collectors.toList());
            String message = String.join(" | ", messages);
            throw new InvalidTradeException(message);
        }

    }
}
