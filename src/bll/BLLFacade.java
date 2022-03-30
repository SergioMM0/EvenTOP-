package bll;

import be.Event;

import java.sql.SQLException;

public interface BLLFacade {

    void addEvent(Event event) throws SQLException;


}
