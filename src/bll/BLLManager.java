package bll;

import be.Event;
import dal.DAO.DAOEvents;

public class BLLManager implements BLLFacade{

    private DAOEvents daoEvents;

    public BLLManager(){
        daoEvents = new DAOEvents();
    }

    @Override
    public void addEvent(Event event){
        daoEvents.addEvent(event);
    }
}
