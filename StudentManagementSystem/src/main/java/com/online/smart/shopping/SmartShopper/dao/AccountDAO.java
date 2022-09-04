package com.online.smart.shopping.SmartShopper.dao;

import com.online.smart.shopping.SmartShopper.entity.Account;
import com.online.smart.shopping.SmartShopper.entity.Order;
import com.online.smart.shopping.SmartShopper.model.OrderInfo;
import com.online.smart.shopping.SmartShopper.pagination.PaginationResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Transactional
@Repository
public class AccountDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Account findAccount(String userName) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.find(Account.class, userName);
    }

    public void saveAccount(Account account) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(account);
    }

    public PaginationResult<Account> listOrderInfo(int page, int maxResult, int maxNavigationPage) {
        String sql = "Select new " + Account.class.getName()//
                + "(acc.name,acc.course) " + " from "
                + Account.class.getName() + " acc "//
                + "  Where user_role = 'USER_ROLE'";

     /*   Map<String, String> st= new HashMap<>();
        st.put("USER_ROLE","USER_ROLE");*/
        Session session = this.sessionFactory.getCurrentSession();
      //  session.find(Account.class,1L,);
        Query<Account> query = session.createQuery(sql, Account.class);
        return new PaginationResult<Account>(query, page, maxResult, maxNavigationPage);
    }

    public Account findByEmail(String email) {
        return new Account();
    }


    public Account findByResetPasswordToken(String token) {
        return new Account();
    }
}
