package com.tradestore.model;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;


public class Trade implements Comparable<Trade> {

    @NotNull(message = "Trade Id can not be empty or null")
    private final String tradeId;
    private final  int version;
    @NotNull(message = "Counter Party Id can not be empty or null")
    private  final String counterPartyId;
    @NotNull(message = "Book Id can not be empty or null")
    private  final String bookId;
    private  final LocalDateTime maturityDate;
    private  final LocalDateTime createdDate;
    private  final Boolean isExpired;

    public Trade(String tradeId, int version, String counterPartyId, String bookId, LocalDateTime maturityDate, LocalDateTime createdDate, Boolean isExpired) {
        this.tradeId = tradeId;
        this.version = version;
        this.counterPartyId = counterPartyId;
        this.bookId = bookId;
        this.maturityDate = maturityDate;
        this.createdDate = createdDate;
        this.isExpired = isExpired;
    }

    public Trade(Builder builder) {
        this.tradeId = builder.tradeId;
        this.version = builder.version;
        this.counterPartyId = builder.counterPartyId;
        this.bookId = builder.bookId;
        this.maturityDate = builder.maturityDate;
        this.createdDate = builder.createdDate;
        this.isExpired = builder.isExpired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return version == trade.version &&
                Objects.equals(tradeId, trade.tradeId) &&
                Objects.equals(counterPartyId, trade.counterPartyId) &&
                Objects.equals(bookId, trade.bookId) &&
                Objects.equals(maturityDate, trade.maturityDate) &&
                Objects.equals(createdDate, trade.createdDate) &&
                Objects.equals(isExpired, trade.isExpired);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeId, version, counterPartyId, bookId, maturityDate, createdDate, isExpired);
    }

    @Override
    public int compareTo(Trade otherTrade) {
        return (this.getVersion() - otherTrade.getVersion());
    }

    public static final class Builder {
        @NotNull(message = "Trade Id can not be empty or null")
        private String tradeId;
        private int version;
        @NotNull(message = "Counter Party Id can not be empty or null")
        private String counterPartyId;
        @NotNull(message = "Book Id can not be empty or null")
        private String bookId;
        private LocalDateTime maturityDate;
        private LocalDateTime createdDate;
        private Boolean isExpired;

        public Builder() {
        }

        public Builder setTradeId(String tradeId) {
            this.tradeId = tradeId;
            return this;
        }

        public Builder setVersion(int version) {
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

        public Trade build() {
            return new Trade(this);
        }

    }


    public String getTradeId() {
        return tradeId;
    }

    public int getVersion() {
        return version;
    }

    public String getCounterPartyId() {
        return counterPartyId;
    }

    public String getBookId() {
        return bookId;
    }

    public LocalDateTime getMaturityDate() {
        return maturityDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public Boolean getExpired() {
        return isExpired;
    }

}
