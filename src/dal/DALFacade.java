package dal;

import be.Event;
import be.User;
import bll.exceptions.BLLException;
import dal.exceptions.DALException;

public interface DALFacade {

    User checkCredentials(String email,String password) throws BLLException, DALException;

    void addEvent(Event event);
}
