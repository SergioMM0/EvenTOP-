package dal;

import be.Event;
import be.User;
import dal.DAO.DAOEventManagers;
import dal.DAO.DAOEvents;
import dal.DAO.DAOLogin;
import dal.DAO.DAOTickets;
import dal.exceptions.DALException;

import java.util.ArrayList;
import java.util.List;

public class DALManager implements DALFacade{

    private DAOLogin daoLogin;
    private DAOEvents daoEvents;
    private DAOEventManagers daoEventManagers;
    private DAOTickets daoTickets;

    public DALManager(){
        daoLogin = new DAOLogin();
        daoEvents = new DAOEvents();
        daoEventManagers = new DAOEventManagers();
        daoTickets = new DAOTickets();
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

    @Override
    public void updateEventAndEms(Event event, List<User> ems) throws DALException {
        daoEvents.updateEventAndEms(event,ems);
    }

    @Override
    public ArrayList<String> getAllExtrasForEvent(Event event) throws DALException {
        return daoTickets.getAllExtrasForEvent(event);
    }

    @Override
    public ArrayList<String> getAllTypesForEvent(Event event) throws DALException {
        return daoTickets.getAllTypesForEvent(event);
    }


}
