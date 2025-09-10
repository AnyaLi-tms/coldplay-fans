package com.oocl.coldplayfans.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserTicketOrderReponse {

    private Integer orderId;
    private String concertName;
    private Integer amount;
    private BigDecimal totalPrice;
    private LocalDateTime purchaseDate;
    private String concertImgUrl;
    private String status;
    List<TicketOrderResponse> ticketOrderList = new ArrayList<>();

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getConcertName() {
        return concertName;
    }

    public void setConcertName(String concertName) {
        this.concertName = concertName;
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

    public String getConcertImgUrl() {
        return concertImgUrl;
    }

    public void setConcertImgUrl(String concertImgUrl) {
        this.concertImgUrl = concertImgUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TicketOrderResponse> getTicketOrderList() {
        return ticketOrderList;
    }

    public void setTicketOrderList(List<TicketOrderResponse> ticketOrderList) {
        this.ticketOrderList = ticketOrderList;
    }

    public UserTicketOrderReponse(Integer orderId, String concertName, Integer amount, BigDecimal totalPrice, LocalDateTime purchaseDate, String concertImgUrl, String status, List<TicketOrderResponse> ticketOrderList) {
        this.orderId = orderId;
        this.concertName = concertName;
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.purchaseDate = purchaseDate;
        this.concertImgUrl = concertImgUrl;
        this.status = status;
        this.ticketOrderList = ticketOrderList;
    }
}
