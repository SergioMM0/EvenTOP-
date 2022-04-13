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
        fixStartHour(event);
        fixEndHour(event);
        dalFacade.addEvent(event);
    }

    public List<User> getAllEms() throws DALException {
        return dalFacade.getAllEms();
    }

    public void addEventAndEMs(Event event, List<User> ems) throws DALException{
        fixStartHour(event);
        fixEndHour(event);
        dalFacade.addEventAndEMs(event,ems);
    }

    public List<Event> getAllEvents()throws DALException {
        return dalFacade.getAllEvents();
    }

    public List<User> getEmsInEvent(Event event) throws DALException{
        return dalFacade.getEmsInEvent(event);
    }

    @Override
    public List<User> getEmsNotInEvent(Event event) throws DALException {
        return dalFacade.getEmsNotInEvent(event);
    }


    public void fixStartHour(Event event){
        String[] wrong = event.getStartTime().split(":");
        if(Integer.parseInt(wrong[0]) < 10 ){
            wrong[0] = "0"+wrong[0];
        }
        if (Integer.parseInt(wrong[1]) < 10){
            wrong[1] = "0"+ wrong[1];
        }
        event.setStartTime(wrong[0],wrong[1]);
    }

    public void fixEndHour(Event event){
        String[] wrong = event.getEndTime().split(":");
        if(Integer.parseInt(wrong[0]) < 10 ){
            wrong[0] = "0"+wrong[0];
        }
        if (Integer.parseInt(wrong[1]) < 10){
            wrong[1] = "0"+ wrong[1];
        }
        event.setEndTime(wrong[0],wrong[1]);
    }
}
