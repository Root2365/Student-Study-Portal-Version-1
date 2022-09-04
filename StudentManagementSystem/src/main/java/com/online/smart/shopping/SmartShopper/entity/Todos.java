package com.online.smart.shopping.SmartShopper.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "todos")
public class Todos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  int id;

    @Column(name = "title",nullable = false, length = 45)
    private String title;

    @Column(name = "description",nullable = false, length = 200)
    private String description;

    @Column(name = "USER_NAME",nullable = false, length = 45)
    private String userName;

    @Column(name = "favourite",nullable = false, length = 45)
    private String favourite;

    @Column(name = "created_date",nullable = false, length = 45)
    private Date createdDate;

/*    @Column(name = "due_date",nullable = false, length = 45)
    private Date dueDate;*/

/*
    @Column(name = "status",nullable = false, length = 45)
    private String status;
*/

/*
    @Column(name = "subject",nullable = false, length = 45)
    private String subject;
*/

    public Todos() {
    }

    public Todos(int id, String title, String description, String userName, Date createdDate, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userName = userName;
        this.createdDate = createdDate;
    }

    public Todos(int id, String title, String description, String userName, String favourite, Date createdDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userName = userName;
        this.favourite = favourite;
        this.createdDate = createdDate;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }




    public String getFavourite() {
        return favourite;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }

    @Override
    public String toString() {
        return "Todos{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", userName='" + userName + '\'' +
                ", favourite='" + favourite + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
