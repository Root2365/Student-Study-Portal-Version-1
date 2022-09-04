package com.online.smart.shopping.SmartShopper.entity;


import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "video")
public class Video implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  int id;

    @Column(name = "title",nullable = false, length = 45)
    private String title;

    @Column(name = "channel",nullable = false, length = 45)
    private String channel;


    @Column(name = "smalldescription",nullable = false, length = 200)
    private String smalldescription;

    @Column(name = "description",nullable = false, length = 2000)
    private String description;

    @Column(name = "created_date",nullable = false, length = 45)
    private Date createdDate;

    @Column(name = "link",nullable = false, length = 45)
    private String link;

    @Column(name = "views",nullable = false, length = 45)
    private String views;

    @Column(name = "duration",nullable = false, length = 45)
    private String duration;

    @Transient
    private MultipartFile thumbnail;

    @Column(name = "thumbnail_location",nullable = false, length = 400)
    private String thumbnailLocation;

    public Video() {
    }

    public Video(int id, String title, String channel, String smalldescription, String description, Date createdDate, String link, String views, String duration, String thumbnailLocation) {
        this.id = id;
        this.title = title;
        this.channel = channel;
        this.smalldescription = smalldescription;
        this.description = description;
        this.createdDate = createdDate;
        this.link = link;
        this.views = views;
        this.duration = duration;
        this.thumbnailLocation = thumbnailLocation;
    }

    public Video(int id, String title, String channel, String smalldescription, String description, Date createdDate, String link, String views, String duration, MultipartFile thumbnail, String thumbnailLocation) {
        this.id = id;
        this.title = title;
        this.channel = channel;
        this.smalldescription = smalldescription;
        this.description = description;
        this.createdDate = createdDate;
        this.link = link;
        this.views = views;
        this.duration = duration;
        this.thumbnail = thumbnail;
        this.thumbnailLocation = thumbnailLocation;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSmalldescription() {
        return smalldescription;
    }

    public void setSmalldescription(String smalldescription) {
        this.smalldescription = smalldescription;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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



}
