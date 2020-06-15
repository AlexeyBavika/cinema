package com.internet.cinema.dao.implementation;

import com.internet.cinema.dao.MovieDao;
import com.internet.cinema.exception.DataProcessingException;
import com.internet.cinema.model.Movie;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl implements MovieDao {
    private static final Logger LOGGER = Logger.getLogger(MovieDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Movie add(Movie movie) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
            LOGGER.info("Movie with id " + movie.getId() + " was added.");
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Failed to add movie with id " + movie.getId(),
                    e);
        }
    }

    @Override
    public Movie get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Movie> query = session.createQuery("FROM Movie WHERE id = :mv_id");
            query.setParameter("mv_id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t get movie with id " + id, e);
        }
    }

    @Override
    public List<Movie> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Movie> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Movie.class);
            criteriaQuery.from(Movie.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Failed to get all movies list.", e);
        }
    }
}
