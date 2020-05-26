package com.internet.cinema.dao.implementation;

import com.internet.cinema.dao.ShoppingCartDao;
import com.internet.cinema.exception.DataProcessingException;
import com.internet.cinema.lib.Dao;
import com.internet.cinema.model.ShoppingCart;
import com.internet.cinema.model.User;
import com.internet.cinema.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private static final Logger LOGGER = Logger.getLogger(ShoppingCartDaoImpl.class);

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            LOGGER.info("shopping cart with id " + shoppingCart.getId()
                    + " was added");
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t add shopping cart with id "
                    + shoppingCart.getId(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<ShoppingCart> query = session
                    .createQuery("FROM ShoppingCart sc LEFT JOIN FETCH sc.tickets "
                            + "WHERE sc.user = :user", ShoppingCart.class);
            query.setParameter("user", user);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t get shopping cart by user id "
                    + user.getId(), e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
            LOGGER.info("shopping cart with id " + shoppingCart.getId()
                    + "was updated");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t update shopping cart with id "
                    + shoppingCart.getId(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
