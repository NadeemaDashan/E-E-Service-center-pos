package dao.custom.impl;

import dao.custom.UserDao;
import dao.util.HibernateUtil;
import entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean saveUser(Users user) throws SQLException, ClassNotFoundException {
        Session session=HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean doesUserExist(String userEmail) throws SQLException, ClassNotFoundException {
        Session session=HibernateUtil.getSession();
        Long count = (Long) session.createQuery("SELECT COUNT(*) FROM Users WHERE email = :userEmail").setParameter("userEmail", userEmail)
                .uniqueResult();
        return count>0;
    }

    @Override
    public boolean save(Users entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Users entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
