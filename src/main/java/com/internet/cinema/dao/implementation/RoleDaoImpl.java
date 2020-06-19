package com.internet.cinema.dao.implementation;

import com.internet.cinema.dao.RoleDao;
import com.internet.cinema.exception.DataProcessingException;
import com.internet.cinema.model.Role;
import com.internet.cinema.model.RoleName;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
    private static final Logger LOGGER = Logger.getLogger(RoleDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role add(Role role) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(role);
            LOGGER.info("Role with id " + role.getRoleId() + " saved");
            transaction.commit();
            return role;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t add role with id " + role
                    .getRoleId(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Role getRoleByName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Role> query = session.createQuery("FROM Role "
                    + "WHERE roleName =: roleName", Role.class);
            query.setParameter("roleName", RoleName.valueOf(roleName));
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t get role with name " + roleName, e);
        }
    }
}
