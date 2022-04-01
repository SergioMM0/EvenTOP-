package dal;

import be.Event;
import be.User;
import bll.exceptions.LoginEX;
import dal.DAO.DAOEvents;
import dal.DAO.DAOLogin;

import java.sql.SQLException;

public class DALManager implements DALFacade{

    DAOLogin daoLogin;
    DAOEvents daoEvents;

    public DALManager(){
        daoLogin = new DAOLogin();
        daoEvents = new DAOEvents();
    }

    @Override
    public User checkCredentials(String email, String password) throws LoginEX {
        return daoLogin.checkCredentials(email,password);
    }

    @Override
    public void addEvent(Event event){
        daoEvents.addEvent(event);
    }

}
