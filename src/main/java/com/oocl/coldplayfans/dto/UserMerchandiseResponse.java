package com.oocl.coldplayfans.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.oocl.coldplayfans.dao.Merchandise;

public class UserMerchandiseResponse {
    private Integer orderId;
    private Integer amount;
    private BigDecimal totalPrice;
    private LocalDateTime purchaseDate;
    private String status;
    private String img_url;
    List<MerchandiseOrderResponse> merchandiseList = new ArrayList<>();


    public UserMerchandiseResponse(Integer orderId, Integer amount, BigDecimal totalPrice, LocalDateTime purchaseDate, String status, List<MerchandiseOrderResponse> merchandiseList, String img_url) {
        this.orderId = orderId;
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.purchaseDate = purchaseDate;
        this.merchandiseList = merchandiseList;
        this.img_url = img_url;
        this.status = status;
    }

    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    public List<MerchandiseOrderResponse> getMerchandiseList() {
        return merchandiseList;
    }
    public void setMerchandiseList(List<MerchandiseOrderResponse> merchandiseList) {
        this.merchandiseList = merchandiseList;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getImgUrl() {
        return img_url;
    }
    public void setImgUrl(String img_url) {
        this.img_url = img_url;
    }

}
