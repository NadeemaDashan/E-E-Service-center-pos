package bo.custom;

import bo.SuperBo;
import dto.UsersDto;
import net.bytebuddy.implementation.bind.annotation.Super;

import java.sql.SQLException;

public interface UsersBo extends SuperBo {
    boolean saveUser(String userEmail,String password) throws SQLException, ClassNotFoundException;
    boolean doesUserExist(String userEmail) throws SQLException, ClassNotFoundException;
    boolean isUserCredentialsValid(String userEmail, String password) throws SQLException, ClassNotFoundException;
}
