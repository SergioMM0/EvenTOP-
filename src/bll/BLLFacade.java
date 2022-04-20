package bll;

import be.Event;
import be.TicketG;
import be.TicketRS;
import be.User;
import dal.exceptions.DALException;

import java.util.ArrayList;
import java.util.List;

public interface BLLFacade {

    void addEvent(Event event) throws DALException;

    List<User> getAllEms() throws DALException;

    void addEventAndEMs(Event event, List<User> ems) throws DALException;

    List<Event> getAllEvents() throws DALException;

    List<User> getEmsInEvent(Event event) throws DALException;

    List<User> getEmsNotInEvent(Event event) throws DALException;

    void updateEventAndEms(Event event, List<User> ems) throws DALException;

    ArrayList<String> getAllExtrasForEvent(Event event) throws DALException;

    ArrayList<String> getAllTypesForEvent(Event event) throws DALException;

    void addTicketRS(TicketRS ticketRS, Event event) throws DALException;

    void addTicketG(TicketG ticketG, Event event) throws DALException;

    boolean checkBarcode(String string) throws DALException;

    void addUser(User user) throws DALException;

    void addTicketRSAndUser(TicketRS ticketRS,Event event,User user) throws DALException;

    void addTicketGAndUser(TicketG ticketG,Event event,User user) throws DALException;

    List<TicketG> getAllTickets() throws DALException;

    void updateTicketType(TicketG ticketG)throws DALException;

    void updateExtrasFromTicket(TicketG ticketg) throws DALException;

    void updateAssistLeaveTime(TicketG ticketG)throws DALException;

    void deleteEvent(Event event)throws DALException;
}
