package bo.custom.impl;

import bo.custom.UsersBo;
import dao.custom.UserDao;
import dao.custom.impl.UserDaoImpl;
import dao.util.HibernateUtil;
import entity.Users;
import org.hibernate.Session;

import java.sql.SQLException;

public class UsersBoImpl implements UsersBo {
    UserDao userDao = new UserDaoImpl();

    public UsersBoImpl() {

    }
    @Override
    public boolean doesUserExist(String userEmail) throws SQLException, ClassNotFoundException {
        return userDao.doesUserExist(userEmail);
    }

    @Override
    public boolean isUserCredentialsValid(String userEmail, String password) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Users user = (Users) session.createQuery("FROM Users WHERE email = :userEmail")
                .setParameter("userEmail", userEmail)
                .uniqueResult();

        return user != null && user.getPassword().equals(password);
    }
    public boolean saveUser(String userEmail,String password) throws SQLException, ClassNotFoundException {
        if (userDao.doesUserExist(userEmail)){
            return false;
        }else {
            Users users = new Users(userEmail,password);
            return userDao.saveUser(users);
        }
    }
}
