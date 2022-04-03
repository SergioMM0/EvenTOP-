package bll;

import be.Event;
import be.User;
import dal.exceptions.DALException;

import java.util.List;

public interface BLLFacade {

    void addEvent(Event event) throws DALException;

    List<User> getAllEms() throws DALException;

}
