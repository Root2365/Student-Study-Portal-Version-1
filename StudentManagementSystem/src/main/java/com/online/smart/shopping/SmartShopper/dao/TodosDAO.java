package com.online.smart.shopping.SmartShopper.dao;

import com.online.smart.shopping.SmartShopper.entity.Account;
import com.online.smart.shopping.SmartShopper.entity.Todos;
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
public class TodosDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Todos findTodos(String id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.find(Todos.class, id);
    }

    public void saveTodos(Todos todos) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(todos);
    }


    public List<Todos> findByUserName(String userName) {
        String hql = " from Todos where userName = :userName";
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql,Todos.class);
        query.setParameter("userName", userName);
        List<Todos> result = query.list();
        result.forEach(System.out::println);
        return result;
    }


    public Account findByResetPasswordToken(String token) {
        return new Account();
    }

    public void updateTodos(Integer todoId, String status) {
        Session session = this.sessionFactory.getCurrentSession();
        Todos todos= session.find(Todos.class, todoId);
/*
        todos.setStatus(status);
*/
        todos.setFavourite(status);
        session.update(todos);
    }
    public void deleteTodos(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(session.find(Todos.class, id));
    }
}
