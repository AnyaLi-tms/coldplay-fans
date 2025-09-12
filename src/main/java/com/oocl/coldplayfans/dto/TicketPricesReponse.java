package com.oocl.coldplayfans.dto;

import java.util.List;

public class TicketPricesReponse {
    private List<Price> priceList;
    private Integer concertId;

    public List<Price> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Price> priceList) {
        this.priceList = priceList;
    }

    public Integer getConcertId() {
        return concertId;
    }

    public void setConcertId(Integer concertId) {
        this.concertId = concertId;
    }
}
