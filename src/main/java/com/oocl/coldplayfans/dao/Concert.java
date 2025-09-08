package com.oocl.coldplayfans.dao;
import java.sql.Date;
import java.sql.Time;
import jakarta.persistence.*;


@Entity
@Table(name = "concert")
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Date startDate;

    private Time startTime;

    private String venue;

    private String city;

    private Date saleDate;

    private Time saleTime;

    private String description;

    private String seatMapUrl;

    public enum Status {
        available,
        sold,
        cancelled,
        ended,
        unreleased
    }

    @Enumerated(EnumType.STRING)
    private Status status;

    private String imgUrl;

    private Boolean isDeleted = false;


    public Concert() {
    }

    public Concert(String name, Date startDate, Time startTime, String venue, String city, Date saleDate, Time saleTime, String description, String seatMapUrl, Status status, String imgUrl) {
        this.name = name;
        this.startDate = startDate;
        this.startTime = startTime;
        this.venue = venue;
        this.city = city;
        this.saleDate = saleDate;
        this.saleTime = saleTime;
        this.description = description;
        this.seatMapUrl = seatMapUrl;
        this.status = status;
        this.imgUrl = imgUrl;
        this.isDeleted = false;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Time getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Time saleTime) {
        this.saleTime = saleTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeatMapUrl() {
        return seatMapUrl;
    }

    public void setSeatMapUrl(String seatMapUrl) {
        this.seatMapUrl = seatMapUrl;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}