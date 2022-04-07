package dal;

import be.Event;
import be.User;
import dal.exceptions.DALException;

import java.util.List;

public interface DALFacade {

    User checkCredentials(String email,String password) throws DALException;

    void addEvent(Event event) throws DALException;

    List<User> getAllEms() throws DALException;

    void addEventAndEMs(Event event, List<User> ems) throws DALException;

    List<Event> getAllEventsB() throws DALException;

    List<User> getEmsInEvent(int id, List<User> allEms) throws DALException;
}
