package com.oocl.coldplayfans.dto;

import java.util.List;

public class TicketPricesReponse {
    private List<Price> priceList;

    public List<Price> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Price> priceList) {
        this.priceList = priceList;
    }
}
