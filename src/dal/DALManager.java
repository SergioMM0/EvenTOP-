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

    public void addEventAndEMs(Event event, List<User> ems) throws DALException{
        daoEvents.addEventAndEMs(event,ems);
    }

    public List<Event> getAllEvents() throws DALException{
        return daoEvents.getAllEvents();
    }

    @Override
    public List<User> getEmsInEvent(Event event) throws DALException {
        return daoEventManagers.getEmsInEvent(event);
    }

    public List<User> getEmsNotInEvent(Event event) throws DALException{
        return daoEventManagers.getEmsNotInEvent(event);
    }

}
