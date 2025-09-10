package com.oocl.coldplayfans.dto;

import java.math.BigDecimal;

public class MerchandiseOrderResponse {
    private Integer merchandiseId;
    private String merchandiseName;
    private BigDecimal merchandisePrice;

    public MerchandiseOrderResponse(Integer merchandiseId, String merchandiseName, BigDecimal merchandisePrice) {
        this.merchandiseId = merchandiseId;
        this.merchandiseName = merchandiseName;
        this.merchandisePrice = merchandisePrice;
    }

    public Integer getMerchandiseId() {
        return merchandiseId;
    }

    public void setMerchandiseId(Integer merchandiseId) {
        this.merchandiseId = merchandiseId;
    }

    public String getMerchandiseName() {
        return merchandiseName;
    }

    public void setMerchandiseName(String merchandiseName) {
        this.merchandiseName = merchandiseName;
    }

    public BigDecimal getMerchandisePrice() {
        return merchandisePrice;
    }

    public void setMerchandisePrice(BigDecimal merchandisePrice) {
        this.merchandisePrice = merchandisePrice;
    }
}
