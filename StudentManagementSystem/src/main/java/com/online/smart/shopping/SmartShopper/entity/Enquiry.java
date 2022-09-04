package com.online.smart.shopping.SmartShopper.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "enquiry")
public class Enquiry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  int id;

    @Column(name = "name",nullable = false, length = 45)
    private String name;

    @Column(name = "message",nullable = false, length = 200)
    private String message;

    @Column(name = "email",nullable = false, length = 45)
    private String email;

    @Column(name = "created_date",nullable = false, length = 45)
    private Date createdDate;

    @Column(name = "subject",nullable = false, length = 45)
    private String subject;

    public Enquiry() {
    }

    public Enquiry(int id, String name, String message, String email, Date createdDate, String subject) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.email = email;
        this.createdDate = createdDate;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
