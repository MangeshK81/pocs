package com.tradestore.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;

public class Trade implements Serializable {

    //Trade Id	Version	Counter-Party Id	Book-Id	Maturity Date	Created Date	Expired
    private String tradeId;
    private long version;
    private String counterPartyId;
    private String bookId;
    private LocalDateTime maturityDate;
    private LocalDateTime createdDate;
    private Boolean isExpired;





}
