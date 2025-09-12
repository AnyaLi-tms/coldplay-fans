package com.oocl.coldplayfans.dto;

import java.math.BigDecimal;

public class Price {
    private BigDecimal price;
    private Integer remainNumber;
    private String seatArea;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getRemainNumber() {
        return remainNumber;
    }

    public void setRemainNumber(Integer remainNumber) {
        this.remainNumber = remainNumber;
    }

    public String getSeatArea() {
        return seatArea;
    }

    public void setSeatArea(String seatArea) {
        this.seatArea = seatArea;
    }
}