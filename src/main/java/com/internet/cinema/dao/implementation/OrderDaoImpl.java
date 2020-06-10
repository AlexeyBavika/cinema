package com.internet.cinema.dao.implementation;

import com.internet.cinema.dao.OrderDao;
import com.internet.cinema.exception.DataProcessingException;
import com.internet.cinema.model.Order;
import com.internet.cinema.model.User;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Order add(Order order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            LOGGER.info("Order with id " + order.getId() + " was added.");
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t add order.", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> query = session.createQuery("SELECT DISTINCT o FROM Order o "
                    + "LEFT JOIN FETCH o.tickets WHERE o.user = :user");
            query.setParameter("user", user);
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t get order history", e);
        }
    }
}
