package dal;

import be.Event;
import be.User;
import bll.exceptions.BLLException;
import dal.DAO.DAOEvents;
import dal.DAO.DAOLogin;
import dal.exceptions.DALException;

public class DALManager implements DALFacade{

    DAOLogin daoLogin;
    DAOEvents daoEvents;

    public DALManager(){
        daoLogin = new DAOLogin();
        daoEvents = new DAOEvents();
    }

    @Override
    public User checkCredentials(String email, String password) throws BLLException, DALException {
        return daoLogin.checkCredentials(email,password);
    }

    @Override
    public void addEvent(Event event){
        daoEvents.addEvent(event);
    }

}
