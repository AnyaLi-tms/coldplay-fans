package com.oocl.coldplayfans.dao;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "concert_id", nullable = false)
    private Integer concertId;

    @Column(name = "id_number", length = 255)
    private String idNumber;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "seat_number", length = 10, nullable = false)
    private String seatNumber;

    @Column(name = "seat_area", length = 10, nullable = false)
    private String seatArea;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "purchase_date", columnDefinition = "datetime DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime purchaseDate;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConcertId() {
        return concertId;
    }

    public void setConcertId(Integer concertId) {
        this.concertId = concertId;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatArea() {
        return seatArea;
    }

    public void setSeatArea(String seatArea) {
        this.seatArea = seatArea;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", concert=" + concertId +
                ", idNumber='" + idNumber + '\'' +
                ", userId=" + userId +
                ", seatNumber='" + seatNumber + '\'' +
                ", seatArea='" + seatArea + '\'' +
                ", price=" + price +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
