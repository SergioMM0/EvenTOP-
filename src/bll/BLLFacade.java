package bll;

import be.Event;
import be.User;
import dal.exceptions.DALException;

import java.util.List;

public interface BLLFacade {

    void addEvent(Event event) throws DALException;

    List<User> getAllEms() throws DALException;

    void addEventAndEMs(Event event, List<User> ems) throws DALException;

    List<Event> getAllEvents() throws DALException;

    List<User> getEmsInEvent(Event event) throws DALException;

    List<User> getEmsNotInEvent(Event event) throws DALException;

}
