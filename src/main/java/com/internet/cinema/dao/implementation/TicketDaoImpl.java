package com.internet.cinema.dao.implementation;

import com.internet.cinema.dao.TicketDao;
import com.internet.cinema.exception.DataProcessingException;
import com.internet.cinema.lib.Dao;
import com.internet.cinema.model.Ticket;
import com.internet.cinema.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class TicketDaoImpl implements TicketDao {
    private static final Logger LOGGER = Logger.getLogger(TicketDaoImpl.class);

    @Override
    public Ticket add(Ticket ticket) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            LOGGER.info("ticket with id " + ticket.getId()
                    + " was added");
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t add ticket with id "
                    + ticket.getId(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
