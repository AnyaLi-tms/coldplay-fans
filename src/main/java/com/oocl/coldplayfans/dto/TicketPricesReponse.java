package com.oocl.coldplayfans.dto;

import java.util.List;

public class TicketPricesReponse {
    private List<Price> priceList;
    private String status;

    public List<Price> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Price> priceList) {
        this.priceList = priceList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
