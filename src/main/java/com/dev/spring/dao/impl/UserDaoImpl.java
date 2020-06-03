package com.dev.spring.dao.impl;

import com.dev.spring.dao.UserDao;
import com.dev.spring.exceptions.DataProcessingException;
import com.dev.spring.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert user entity", e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> listUsers() {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            Query<User> query = session
                    .createQuery("select u from User u", User.class);
            return query.getResultList();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't find user by email", e);
        } finally {
            session.close();
        }
    }
}
