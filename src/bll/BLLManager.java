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
    private HourFixer hourFixer;

    public BLLManager(){
        dalFacade = new DALManager();
        hourFixer = hourFixer.getInstance();
    }

    @Override
    public void addEvent(Event event) throws DALException {
        hourFixer.fixStartHour(event);
        hourFixer.fixEndHour(event);
        dalFacade.addEvent(event);
    }

    public List<User> getAllEms() throws DALException {
        return dalFacade.getAllEms();
    }

    public void addEventAndEMs(Event event, List<User> ems) throws DALException{
        hourFixer.fixStartHour(event);
        hourFixer.fixEndHour(event);
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
        hourFixer.fixStartHour(event);
        hourFixer.fixEndHour(event);
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
        hourFixer.fixTicketRSAssist(ticketRS);
        hourFixer.fixTicketRSLeave(ticketRS);
        ticketRS.setBarCode(safeBarcode().toString());
        dalFacade.addTicketRS(ticketRS,event);
    }

    public void addTicketRSAndUser(TicketRS ticketRS,Event event,User user) throws DALException{
        ticketRS.setBarCode(safeBarcode().toString());
        user.setPassword(getBarcodePassword(ticketRS.getBarCode()));
        addUser(user);
        dalFacade.addTicketRS(ticketRS,event);
    }

    @Override
    public void addTicketG(TicketG ticketG, Event event) throws DALException {
        hourFixer.fixTicketGAssist(ticketG);
        hourFixer.fixTicketGLeave(ticketG);
        ticketG.setBarCode(safeBarcode().toString());
        dalFacade.addTicketG(ticketG,event);
    }

    public void addTicketGAndUser(TicketG ticketG,Event event,User user) throws DALException{
        hourFixer.fixTicketGAssist(ticketG);
        hourFixer.fixTicketGLeave(ticketG);
        ticketG.setBarCode(safeBarcode().toString());
        user.setPassword(getBarcodePassword(ticketG.getBarCode()));
        addUser(user);
        dalFacade.addTicketG(ticketG,event);
    }

    @Override
    public boolean checkBarcode(String string) throws DALException {
        return dalFacade.checkBarcode(string);
    }

    @Override
    public void addUser(User user) throws DALException {
        dalFacade.addUser(user);
    }

    private String getBarcodePassword(String barcode){
        int length = barcode.length();
        return barcode.substring(length-10);
    }

    private UUID safeBarcode() throws DALException {
        UUID uuid = UUID.randomUUID();
        while(uuidIsTaken(uuid)){
            uuidIsTaken(uuid = UUID.randomUUID());
        }
        return uuid;
    }

    private boolean uuidIsTaken(UUID uuid) throws DALException{
        return checkBarcode(uuid.toString());//true if taken & false if not
    }


}
