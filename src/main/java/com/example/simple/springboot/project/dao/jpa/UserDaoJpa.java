package com.example.simple.springboot.project.dao.jpa;

import com.example.simple.springboot.project.dao.UserDao;
import com.example.simple.springboot.project.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoJpa implements UserDao {
    private static Logger logger = LoggerFactory.getLogger(UserDaoJpa.class);

    @PersistenceContext
    private javax.persistence.EntityManager em;

    @Override
    public void save(User user) {
        em.merge(user);
        em.flush();
        if (logger.isDebugEnabled()) {
            logger.debug("User saved with data: " + user);
        }
    }

    @Override
    public User getByName(String name) {
        User result;
        Query query = em
                .createQuery("select u from User u where u.name = :name")
                .setParameter("name", name);
        List<User> users = query.getResultList();
        if (users == null || users.size() == 0) {
            return null;
        } else {
            result = users.get(0);
        }
        return result;
    }

    @Override
    public User getById(String id) {
        User result;
        Query query = em
                .createQuery("select u from User u where u.id = :id")
                .setParameter("id", id);
        List<User> users = query.getResultList();
        if (users == null || users.size() == 0) {
            return null;
        } else {
            result = users.get(0);
        }
        return result;
    }

}
