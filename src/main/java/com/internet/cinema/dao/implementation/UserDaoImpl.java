package com.internet.cinema.dao.implementation;

import com.internet.cinema.dao.UserDao;
import com.internet.cinema.exception.DataProcessingException;
import com.internet.cinema.lib.Dao;
import com.internet.cinema.model.User;
import com.internet.cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public User add(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Long userId = (Long) session.save(user);
            user.setId(userId);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t add user.", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public User findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE email = :eml");
            query.setParameter("eml", email);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t find user by email " + email, e);
        }
    }
}
