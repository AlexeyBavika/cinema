package com.internet.cinema.dao.implementation;

import com.internet.cinema.dao.UserDao;
import com.internet.cinema.exception.DataProcessingException;
import com.internet.cinema.model.User;
import java.util.Optional;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User add(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            LOGGER.info("User with id " + user.getId() + " was added.");
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
    public Optional<User> findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE email = :eml");
            query.setParameter("eml", email);
            return query.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t find user by email " + email, e);
        }
    }
}
