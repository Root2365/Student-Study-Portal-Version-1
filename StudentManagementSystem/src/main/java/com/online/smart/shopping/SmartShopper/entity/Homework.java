package com.online.smart.shopping.SmartShopper.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "homework")
public class Homework implements Serializable {

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

    @Column(name = "created_date",nullable = false, length = 45)
    private Date createdDate;

    @Column(name = "due_date",nullable = false, length = 45)
    private Date dueDate;

    @Column(name = "status",nullable = false, length = 45)
    private String status;

    @Column(name = "subject",nullable = false, length = 45)
    private String subject;

    public Homework() {
    }

    public Homework(int id, String title, String description, String userName, Date createdDate, Date dueDate, String status, String subject) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userName = userName;
        this.createdDate = createdDate;
        this.dueDate = dueDate;
        this.status = status;
        this.subject = subject;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", userName='" + userName + '\'' +
                ", createdDate=" + createdDate +
                ", dueDate=" + dueDate +
                ", status='" + status + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
