package com.online.smart.shopping.SmartShopper.dao;

import com.online.smart.shopping.SmartShopper.entity.Account;
import com.online.smart.shopping.SmartShopper.entity.Notes;
import com.online.smart.shopping.SmartShopper.entity.Wiki;
import com.online.smart.shopping.SmartShopper.pagination.PaginationResult;
import org.aspectj.weaver.ast.Not;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class NotesDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Notes findNotes(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.find(Notes.class, id);
    }

    public void saveNotes(Notes notes) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(notes);
    }

    public void updateNotes(Notes notes) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(notes);
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

    public List<Notes> findByUserName(String userName) {
        String hql = " from Notes where userName = :userName";
        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createQuery(hql,Notes.class);
        query.setParameter("userName", userName);

        List<Notes> result = query.list();
        result.forEach(System.out::println);
        return result;
    }


    public Account findByResetPasswordToken(String token) {
        return new Account();
    }

    public void deleteNotes(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        Notes notes= session.find(Notes.class, id);
        session.delete(notes);
    }
}
