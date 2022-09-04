package com.online.smart.shopping.SmartShopper.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "notes")
public class Notes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  int id;

    private String title;

    @Column(name = "description",nullable = false, length = 45)
    private String description;

    @Column(name = "USER_NAME",nullable = false, length = 45)
    private String userName;

    @Column(name = "created_date",nullable = false, length = 45)
    private Date createdDate;

    @Column(name = "due_date",nullable = false, length = 45)
    private Date dueDate;

    public Notes() {
    }

    public Notes(int id, String title, String description, String userName, Date createdDate, Date dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userName = userName;
        this.createdDate = createdDate;
        this.dueDate = dueDate;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", userName='" + userName + '\'' +
                ", createdDate=" + createdDate +
                ", dueDate=" + dueDate +
                '}';
    }
}
