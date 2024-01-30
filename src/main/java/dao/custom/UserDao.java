package dao.custom;

import entity.Users;

import java.sql.SQLException;

public interface UserDao {
    boolean saveUser(Users user) throws SQLException, ClassNotFoundException;
    boolean doesUserExist(String userEmail) throws SQLException, ClassNotFoundException;
}
