package be;

import java.util.Date;
import java.util.List;

public class Event {

    private int id;
    private String name;
    private Date date;
    private List<User> eventManagers;
    private String location;
    private int tickets;
    private String info;
    private String startTime;
    private String endTime;

    public Event(int id, String name, Date date, List<User> eventManagers, String location, int tickets, String info) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.eventManagers = eventManagers;
        this.location = location;
        this.tickets = tickets;
        this.info = info;
    }

    public Event (int id, String name, Date date, String location, String info){
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this. info = info;
    }

    public Event(String name, Date date, String location, String info,String startTime,String endTime){
        this.name = name;
        this.date = date;
        this.location = location;
        this.info = info;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public void addEventManager(User user) {
        eventManagers.add(user);
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String hour,String min) {
        this.startTime = hour + ":" + min;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String hour, String min) {
        this.endTime = hour + ":" + min;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", eventManagers=" + eventManagers +
                ", location='" + location + '\'' +
                ", tickets=" + tickets +
                ", info='" + info + '\'' +
                '}';
    }
}
