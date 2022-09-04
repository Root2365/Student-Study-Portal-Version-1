package com.online.smart.shopping.SmartShopper.dao;

import com.online.smart.shopping.SmartShopper.entity.Account;
import com.online.smart.shopping.SmartShopper.entity.Enquiry;
import com.online.smart.shopping.SmartShopper.entity.Homework;
import com.online.smart.shopping.SmartShopper.pagination.PaginationResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class EnquiryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveEnquiry(Enquiry enquiry) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(enquiry);
    }
}
