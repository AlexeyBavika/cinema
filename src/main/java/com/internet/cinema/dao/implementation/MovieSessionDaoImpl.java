package com.internet.cinema.dao.implementation;

import com.internet.cinema.dao.MovieSessionDao;
import com.internet.cinema.exception.DataProcessingException;
import com.internet.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    private static final Logger LOGGER = Logger.getLogger(MovieSessionDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> query = session.createQuery("FROM MovieSession "
                    + "WHERE show_time = :time");
            query.setParameter("time", date);
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t find available sessions.", e);
        }
    }

    @Override
    public MovieSession get(Long movieSessionId) {
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> query = session
                    .createQuery("FROM MovieSession WHERE id = :mvsn_id");
            query.setParameter("mvsn_id", movieSessionId);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t get movie session with id "
                    + movieSessionId, e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            LOGGER.info("movie session with id " + movieSession.getId() + " was added.");
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t add movie session.", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
