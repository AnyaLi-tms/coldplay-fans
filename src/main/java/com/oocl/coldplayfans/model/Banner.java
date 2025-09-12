package com.oocl.coldplayfans.model;
import jakarta.persistence.*;


@Entity
@Table(name = "banner")
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String name;

    @Column(name = "img_url", length = 2048)
    private String imgUrl;

    @Column(length = 2048)
    private String link;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column
    private Boolean status;

    public Banner() {
    }
    
    public Banner(String name, String imgUrl, String link, Boolean status) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.link = link;
        this.status = status;
        this.isDeleted = false;
    }

    // Getters and setters
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
    public String getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public Boolean getIsDeleted() {
        return isDeleted;
    }
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }

}