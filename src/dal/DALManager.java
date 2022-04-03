package dal;

import be.Event;
import be.User;
import bll.exceptions.BLLException;
import dal.DAO.DAOEventManagers;
import dal.DAO.DAOEvents;
import dal.DAO.DAOLogin;
import dal.exceptions.DALException;

import java.util.List;

public class DALManager implements DALFacade{

    private DAOLogin daoLogin;
    private DAOEvents daoEvents;
    private DAOEventManagers daoEventManagers;

    public DALManager(){
        daoLogin = new DAOLogin();
        daoEvents = new DAOEvents();
        daoEventManagers = new DAOEventManagers();
    }

    @Override
    public User checkCredentials(String email, String password) throws DALException {
        return daoLogin.checkCredentials(email,password);
    }

    @Override
    public void addEvent(Event event) throws DALException {
        daoEvents.addEvent(event);
    }

    @Override
    public List<User> getAllEms() throws DALException {
        return daoEventManagers.getAllEms();
    }

}
