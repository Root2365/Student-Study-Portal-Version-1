package com.online.smart.shopping.SmartShopper.dao;

import com.online.smart.shopping.SmartShopper.entity.Video;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class VideoDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveVideo(Video video) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(video);
    }

    public void deleteVideo(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        Video video= session.find(Video.class, id);
        session.delete(video);
    }

    public void updateVideo(Video video) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(video);
    }

    public List<Video> findByTitle(String title) {
        title="%"+title+"%";
        String hql = " from Video where title like :title";
        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createQuery(hql,Video.class);
        query.setParameter("title", title);

        List<Video> result = query.list();
        result.forEach(System.out::println);

        return result;
    }

    public Video findVideo(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.find(Video.class, id);
    }


}
