package com.online.smart.shopping.SmartShopper.entity;


import javax.persistence.*;
import java.io.Serializable;
/**
 * create table ORDERS
 * (
 *   ID               VARCHAR(50) not null,
 *   AMOUNT           double precision not null,
 *   CUSTOMER_ADDRESS VARCHAR(255) not null,
 *   CUSTOMER_EMAIL   VARCHAR(128) not null,
 *   CUSTOMER_NAME    VARCHAR(255) not null,
 *   CUSTOMER_PHONE   VARCHAR(128) not null,
 *   ORDER_DATE       datetime not null,
 *   ORDER_NUM        INTEGER not null
 * ) ;
 * **/


@Entity
@Table(name = "Store")
public class Store implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private  int id;

    private String name;

    @Column(name = "open_time",nullable = false, length = 45)
    private String openTime;

    @Column(name = "close_time",nullable = false, length = 45)
    private String closeTime;

    private String location;

    public Store() {
    }

    public Store(int id) {
        this.id = id;
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

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", openTime='" + openTime + '\'' +
                ", closeTime='" + closeTime + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
