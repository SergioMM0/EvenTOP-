package be;

import java.util.Date;

public class Ticket {

    private int id;
    private String barCode;
    private String type;
    private String extras;
    private Date date;
    private String location;
    private String startTime;
    private String endTime;


    public Ticket(int id, String barCode, String type, String extras,Date date, String location, String startTime, String endTime){
        this.id = id;
        this.barCode = barCode;
        this.type = type;
        this.extras = extras;
        this.date = date;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", barCode='" + barCode + '\'' +
                ", type='" + type + '\'' +
                ", extras='" + extras + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
