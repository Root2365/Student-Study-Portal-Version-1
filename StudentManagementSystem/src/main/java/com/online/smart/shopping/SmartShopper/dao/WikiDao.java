package com.online.smart.shopping.SmartShopper.dao;

import com.online.smart.shopping.SmartShopper.entity.Enquiry;
import com.online.smart.shopping.SmartShopper.entity.Homework;
import com.online.smart.shopping.SmartShopper.entity.Video;
import com.online.smart.shopping.SmartShopper.entity.Wiki;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class WikiDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveWiki(Wiki wiki) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(wiki);
    }

    public void updateWiki(Wiki wiki) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(wiki);
    }

    public List<Wiki> findByTitle(String title) {
        title="%"+title+"%";
        String hql = " from Wiki where title like :title";
        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createQuery(hql,Wiki.class);
        query.setParameter("title", title);

        List<Wiki> result = query.list();

        session.close();
        return result;
    }

    public Wiki findWiki(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.find(Wiki.class, id);
    }


    public void deleteWiki(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        Wiki wiki= session.find(Wiki.class, id);
        session.delete(wiki);
    }
}
