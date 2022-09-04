package com.online.smart.shopping.SmartShopper.entity;


import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "wiki")
public class Wiki implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  int id;

    @Column(name = "title",nullable = false, length = 45)
    private String title;

    @Column(name = "description",nullable = false, length = 200)
    private String description;

    @Column(name = "smalldescription",nullable = false, length = 200)
    private String smalldescription;

    @Column(name = "created_date",nullable = false, length = 45)
    private Date createdDate;

    @Column(name = "link",nullable = false, length = 45)
    private String link;

    @Transient
    private MultipartFile thumbnail;

    @Column(name = "thumbnail_location",nullable = false, length = 400)
    private String thumbnailLocation;


    public Wiki() {
    }

    public Wiki(int id, String title, String description, String smalldescription, Date createdDate, String link, MultipartFile thumbnail, String thumbnailLocation) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.smalldescription = smalldescription;
        this.createdDate = createdDate;
        this.link = link;
        this.thumbnail = thumbnail;
        this.thumbnailLocation = thumbnailLocation;
    }

    public Wiki(int id, String title, String description, String smalldescription, Date createdDate, String link, String thumbnail) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.smalldescription = smalldescription;
        this.createdDate = createdDate;
        this.link = link;
        this.thumbnailLocation = thumbnail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public String getSmalldescription() {
        return smalldescription;
    }

    public void setSmalldescription(String smalldescription) {
        this.smalldescription = smalldescription;
    }

    public MultipartFile getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MultipartFile thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnailLocation() {
        return thumbnailLocation;
    }

    public void setThumbnailLocation(String thumbnailLocation) {
        this.thumbnailLocation = thumbnailLocation;
    }
}
