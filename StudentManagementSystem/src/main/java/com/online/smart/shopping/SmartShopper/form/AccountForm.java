package com.online.smart.shopping.SmartShopper.form;

import com.online.smart.shopping.SmartShopper.entity.Account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


public class AccountForm implements Serializable {

    private static final long serialVersionUID = -2054386655979281969L;

    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_EMPLOYEE = "EMPLOYEE";

    private String userName;

    private String encrytedPassword;

   private String  email;

    private boolean active;

   private String userRole;

   private String course;

    public AccountForm() {
    }

    public AccountForm(String userName) {
        this.userName = userName;
    }

    public AccountForm(Account userName) {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncrytedPassword() {
        return encrytedPassword;
    }

    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getEmail() {
        return email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AccountForm{" +
                "userName='" + userName + '\'' +
                ", encrytedPassword='" + encrytedPassword + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", userRole='" + userRole + '\'' +
                ", course='" + course + '\'' +
                '}';
    }
}
