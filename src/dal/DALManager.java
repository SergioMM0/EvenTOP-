package dal;

import be.Event;
import be.TicketG;
import be.TicketRS;
import be.User;
import dal.DAO.*;
import dal.exceptions.DALException;

import java.util.ArrayList;
import java.util.List;

public class DALManager implements DALFacade{

    private final DAOLogin daoLogin;
    private static DAOEvents daoEvents;
    private static DAOEventManagers daoEventManagers;
    private static DAOTickets daoTickets;
    private static DAOUsers daoUsers;

    public DALManager(){
        daoLogin = DAOLogin.instance.getInstance();
        daoEvents = new DAOEvents();
        daoEventManagers = new DAOEventManagers();
        daoTickets = new DAOTickets();
        daoUsers = new DAOUsers();
    }

    @Override
    public User checkCredentials(String email, String password) throws DALException {
        return daoLogin.checkCredentials(email,password);
    }

    @Override
    public void addEvent(Event event) throws DALException {
        daoEvents.addEvent(event);
    }

    @Override
    public List<User> getAllEms() throws DALException {
        return daoEventManagers.getAllEms();
    }

    public void addEventAndEMs(Event event, List<User> ems) throws DALException{
        daoEvents.addEventAndEMs(event,ems);
    }

    public List<Event> getAllEvents() throws DALException{
        return daoEvents.getAllEvents();
    }

    @Override
    public List<User> getEmsInEvent(Event event) throws DALException {
        return daoEventManagers.getEmsInEvent(event);
    }

    public List<User> getEmsNotInEvent(Event event) throws DALException{
        return daoEventManagers.getEmsNotInEvent(event);
    }

    @Override
    public void updateEventAndEms(Event event, List<User> ems) throws DALException {
        daoEvents.updateEventAndEms(event,ems);
    }

    @Override
    public ArrayList<String> getAllExtrasForEvent(Event event) throws DALException {
        return daoTickets.getAllExtrasForEvent(event);
    }

    @Override
    public ArrayList<String> getAllTypesForEvent(Event event) throws DALException {
        return daoTickets.getAllTypesForEvent(event);
    }

    @Override
    public void addTicketRS(TicketRS ticketRS, Event event) throws DALException {
        daoTickets.addTicketRS(ticketRS,event);
    }

    @Override
    public void addTicketG(TicketG ticketG, Event event) throws DALException {
        daoTickets.addTicketG(ticketG,event);
    }

    @Override
    public boolean checkBarcode(String string) throws DALException {
        return daoTickets.checkBarcode(string);
    }

    @Override
    public void addUser(User user) throws DALException {
        daoUsers.addUser(user);
    }

    @Override
    public List<TicketG> getAllTickets() throws DALException {
        return daoTickets.getAllTickets();
    }

    @Override
    public void updateTicketType(TicketG ticketG) throws DALException {
        daoTickets.updateTicketType(ticketG);
    }

    @Override
    public void updateExtrasFromTicket(TicketG ticketg) throws DALException {
        daoTickets.updateExtrasFromTicket(ticketg);
    }

    @Override
    public void updateAssistLeaveTime(TicketG ticketG) throws DALException {
        daoTickets.updateAssistLeaveTime(ticketG);
    }

    @Override
    public void deleteEvent(Event event) throws DALException {
        daoEvents.deleteEvent(event);
    }

    public User checkBarCodeExists(String barcode)throws DALException{
        return daoLogin.checkBarCodeExists(barcode);
    }

}
