package bo.custom.impl;

import bo.SuperBo;
import bo.custom.UsersBo;
import dto.UsersDto;

import java.sql.SQLException;

public class UsersBoImpl implements UsersBo {

    @Override
    public boolean saveUser(UsersDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean doesUserExist(String userEmail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean isUserCredentialsValid(String userEmail, String password) throws SQLException, ClassNotFoundException {
        return false;
    }
}
