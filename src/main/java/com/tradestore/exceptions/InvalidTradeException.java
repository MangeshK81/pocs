package com.tradestore.exceptions;

import java.util.List;

public class InvalidTradeException extends Exception {
    private String message;
    public InvalidTradeException(){
        super();
    }
    public InvalidTradeException(String message){
        super(message);
        this.message = message;
    }

    public InvalidTradeException(List<String> messages) {
    }
}
