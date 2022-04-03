package bll;

import be.Event;
import be.User;
import dal.DALFacade;
import dal.DALManager;
import dal.exceptions.DALException;

import java.util.List;

public class BLLManager implements BLLFacade{

    private DALFacade dalFacade;

    public BLLManager(){
        dalFacade = new DALManager();
    }

    @Override
    public void addEvent(Event event) throws DALException {
        dalFacade.addEvent(event);
    }

    public List<User> getAllEms() throws DALException {
        return dalFacade.getAllEms();
    }

    public void addEventAndEMs(Event event, List<User> ems) throws DALException{
        dalFacade.addEventAndEMs(event,ems);
    }
}
