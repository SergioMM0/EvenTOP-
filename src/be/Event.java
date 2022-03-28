package be;

import java.util.Date;
import java.util.List;

public class Event {

    private int id;
    private String name;
    private Date date;
    private List<User> eventManagers;
    private String location;
    private List<Ticket> tickets;
    private String[] info;

    public Event(int id, String name, Date date, List<User> eventManagers, String location, List<Ticket> tickets, String[] info) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.eventManagers = eventManagers;
        this.location = location;
        this.tickets = tickets;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<User> getEventManagers() {
        return eventManagers;
    }

    public void addEventManager(List<User> eventManagers, User user) {
        eventManagers.add(user);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(List<Ticket> tickets, Ticket ticket) {
        tickets.add(ticket);
    }

    public int countTickets(List<Ticket> list) {
        return list.size();
    }

    public String[] getInfo() {
        return info;
    }

    public void setInfo(String[] info) {
        this.info = info;
    }
}
