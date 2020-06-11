package com.internet.cinema.dao.implementation;

import com.internet.cinema.dao.CinemaHallDao;
import com.internet.cinema.exception.DataProcessingException;
import com.internet.cinema.model.CinemaHall;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl implements CinemaHallDao {
    private static final Logger LOGGER = Logger.getLogger(CinemaHallDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.save(cinemaHall);
            transaction.commit();
            LOGGER.info("cinema hall with id " + cinemaHall.getId() + " was added.");
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t add cinema hall", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<CinemaHall> query = session.createQuery("FROM CinemaHall ");
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t get all cinema halls", e);
        }
    }

    @Override
    public CinemaHall get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<CinemaHall> query = session.createQuery("FROM CinemaHall WHERE id = :cnmhl_id");
            query.setParameter("cnmhl_id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t get cinema hall with id " + id, e);
        }
    }
}
