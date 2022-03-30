package bll;

import be.Event;
import dal.DAO.DAOEvents;

import java.sql.SQLException;

public class BLLManager implements BLLFacade{

    private DAOEvents daoEvents;

    public BLLManager(){
        daoEvents = new DAOEvents();
    }

    @Override
    public void addEvent(Event event) throws SQLException {
        daoEvents.addEvent(event);
    }
}
