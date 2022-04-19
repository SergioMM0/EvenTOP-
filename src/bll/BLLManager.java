package bll;

import be.Event;
import be.TicketG;
import be.TicketRS;
import be.User;
import dal.DALFacade;
import dal.DALManager;
import dal.DAO.DAOTickets;
import dal.exceptions.DALException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Override
    public void updateEventAndEms(Event event, List<User> ems) throws DALException {
        fixStartHour(event);
        fixEndHour(event);
        dalFacade.updateEventAndEms(event,ems);
    }

    @Override
    public ArrayList<String> getAllExtrasForEvent(Event event) throws DALException {
        return dalFacade.getAllExtrasForEvent(event);
    }

    @Override
    public ArrayList<String> getAllTypesForEvent(Event event) throws DALException {
        return dalFacade.getAllTypesForEvent(event);
    }

    @Override
    public void addTicketRS(TicketRS ticketRS, Event event) throws DALException {
        ticketRS.setBarCode(safeBarcode().toString());
        dalFacade.addTicketRS(ticketRS,event);
    }

    @Override
    public void addTicketG(TicketG ticketG, Event event) throws DALException {
        ticketG.setBarCode(safeBarcode().toString());
        dalFacade.addTicketG(ticketG,event);
    }

    @Override
    public boolean checkBarcode(String string) throws DALException {
        return dalFacade.checkBarcode(string);
    }

    public UUID safeBarcode() throws DALException {
        UUID uuid = UUID.randomUUID();
        while(uuidIsTaken(uuid)){
            uuidIsTaken(uuid = UUID.randomUUID());
        }
        return uuid;
    }

    public boolean uuidIsTaken(UUID uuid) throws DALException{
        return checkBarcode(uuid.toString());//true if taken & false if not
    }

    public void fixStartHour(Event event){
        String[] wrong = event.getStartTime().split(":");
        parseAndCompare(wrong);
        event.setStartTime(wrong[0],wrong[1]);
    }

    public void fixEndHour(Event event){
        String[] wrong = event.getEndTime().split(":");
        parseAndCompare(wrong);
        event.setEndTime(wrong[0],wrong[1]);
    }

    private void parseAndCompare(String[] wrong) {
        if(Integer.parseInt(wrong[0]) < 10 && wrong[0].length() == 1){
            wrong[0] = "0"+wrong[0];
        }
        if (Integer.parseInt(wrong[1]) < 10 && wrong[1].length() == 1){
            wrong[1] = "0"+ wrong[1];
        }
    }


}
