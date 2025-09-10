package com.oocl.coldplayfans.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "merchandise")
public class Merchandise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    
    @Column(name = "user_id")
    private Integer userId;

    private BigDecimal price;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    private String status;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "img_url")
    private String imgUrl;

    private String description;


    // Constructors
    public Merchandise() {
    }

    public Merchandise(String name, BigDecimal price, String imgUrl, String description) {
        this.name = name;
        this.price = price;
        this.status = "instock";
        this.isDeleted = false;
        this.imgUrl = imgUrl;
        this.description = description;
    }

    public Merchandise(String name, Integer userId, BigDecimal price, LocalDateTime purchaseDate, String imgUrl, String description) {
        this.name = name;
        this.userId = userId;
        this.price = price;
        this.purchaseDate = purchaseDate;
        this.status = "purchased";
        this.isDeleted = false;
        this.imgUrl = imgUrl;
        this.description = description;
    }


    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}