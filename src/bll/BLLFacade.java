package bll;

import be.Event;
import be.User;
import dal.exceptions.DALException;

import java.util.ArrayList;
import java.util.List;

public interface BLLFacade {

    void addEvent(Event event) throws DALException;

    List<User> getAllEms() throws DALException;

    void addEventAndEMs(Event event, List<User> ems) throws DALException;

    List<Event> getAllEvents() throws DALException;

    List<User> getEmsInEvent(Event event) throws DALException;

    List<User> getEmsNotInEvent(Event event) throws DALException;

    void updateEventAndEms(Event event, List<User> ems) throws DALException;

    ArrayList<String> getAllExtrasForEvent(Event event) throws DALException;

    ArrayList<String> getAllTypesForEvent(Event event) throws DALException;
}
