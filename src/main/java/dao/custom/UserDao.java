package dao.custom;

import dao.CrudDao;
import entity.Users;

import java.sql.SQLException;

public interface UserDao extends CrudDao<Users> {
    boolean saveUser(Users user) throws SQLException, ClassNotFoundException;
    boolean doesUserExist(String userEmail) throws SQLException, ClassNotFoundException;
}
