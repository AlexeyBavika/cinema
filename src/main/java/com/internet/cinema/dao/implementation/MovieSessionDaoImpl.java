package com.internet.cinema.dao.implementation;

import com.internet.cinema.dao.MovieSessionDao;
import com.internet.cinema.exception.DataProcessingException;
import com.internet.cinema.lib.Dao;
import com.internet.cinema.model.MovieSession;
import com.internet.cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    private static final Logger LOGGER = Logger.getLogger(MovieSessionDaoImpl.class);

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> query = session.createQuery("FROM MovieSession "
                    + "WHERE show_time = :time");
            query.setParameter("time", date);
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t find available sessions.", e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Long movieSessionId = (Long) session.save(movieSession);
            transaction.commit();
            movieSession.setId(movieSessionId);
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
