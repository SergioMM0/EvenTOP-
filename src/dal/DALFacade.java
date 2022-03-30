package dal;

import be.Event;
import be.User;
import bll.exceptions.LoginEX;

import java.sql.SQLException;

public interface DALFacade {

    User checkCredentials(String email,String password) throws LoginEX;

    void addEvent(Event event) throws SQLException;
}
