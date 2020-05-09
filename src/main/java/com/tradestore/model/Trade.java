package com.tradestore.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;


public class Trade implements Serializable {

    //Trade Id	Version	Counter-Party Id	Book-Id	Maturity Date	Created Date	Expired
    @NotNull(message = "Trade Id can not be empty or null")
    private String tradeId;
    private long version;
    @NotNull(message = "Counter Party Id can not be empty or null")
    private String counterPartyId;
    @NotNull(message = "Book Id can not be empty or null")
    private String bookId;
    private LocalDateTime maturityDate;
    private LocalDateTime createdDate;
    private Boolean isExpired;

    public Trade(String tradeId, long version, String counterPartyId, String bookId, LocalDateTime maturityDate, LocalDateTime createdDate, Boolean isExpired) {
        this.tradeId = tradeId;
        this.version = version;
        this.counterPartyId = counterPartyId;
        this.bookId = bookId;
        this.maturityDate = maturityDate;
        this.createdDate = createdDate;
        this.isExpired = isExpired;
    }

    public static class Builder{
        @NotNull(message = "Trade Id can not be empty or null")
        private String tradeId;
        private long version;
        @NotNull(message = "Counter Party Id can not be empty or null")
        private String counterPartyId;
        @NotNull(message = "Book Id can not be empty or null")
        private String bookId;
        private LocalDateTime maturityDate;
        private LocalDateTime createdDate;
        private Boolean isExpired;

        public Builder(){}
        public Builder setTradeId(String tradeId) {
            this.tradeId = tradeId;
            return this;
        }

        public Builder setVersion(long version) {
            this.version = version;
            return this;
        }

        public Builder setCounterPartyId(String counterPartyId) {
            this.counterPartyId = counterPartyId;
            return this;
        }

        public Builder setBookId(String bookId) {
            this.bookId = bookId;
            return this;
        }

        public Builder setMaturityDate(LocalDateTime maturityDate) {
            this.maturityDate = maturityDate;
            return this;
        }

        public Builder setCreatedDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder setExpired(Boolean expired) {
            isExpired = expired;
            return this;
        }

        public Trade build(){
            return new Trade(tradeId, version, counterPartyId, bookId, maturityDate, createdDate, isExpired) ;
        }

    }




}
