package com.online.smart.shopping.SmartShopper.dao;

import com.online.smart.shopping.SmartShopper.entity.Account;
import com.online.smart.shopping.SmartShopper.entity.Store;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class StoreDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Store findStore(String userName) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.find(Store.class, userName);
    }

    public void saveStore(Store account) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(account);
    }

    public void deleteStore(Store account) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(account);
    }
}

