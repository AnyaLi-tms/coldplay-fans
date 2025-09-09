package com.oocl.coldplayfans.dao;
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

    private Double price;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    private String status;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "img_url")
    private String imgUrl;

    private String description;


    // Constructors
    public Merchandise() {
    }

    public Merchandise(String name, Double price, String imgUrl, String description) {
        this.name = name;
        this.price = price;
        this.status = "instock";
        this.isDeleted = false;
        this.imgUrl = imgUrl;
        this.description = description;
    }

    public Merchandise(String name, Integer userId, Double price, LocalDateTime purchaseDate, String imgUrl, String description) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
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