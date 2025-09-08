package com.oocl.coldplayfans.dto;

import java.math.BigDecimal;

public class Price {
    private BigDecimal price;
    private Integer remainNumber;

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
}