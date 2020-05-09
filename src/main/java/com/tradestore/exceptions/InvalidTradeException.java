package com.tradestore.exceptions;

public class InvalidTradeException extends Exception {
    private String message;
    public InvalidTradeException(){
        super();
    }
    public InvalidTradeException(String message){
        super(message);
        this.message = message;
    }

}
