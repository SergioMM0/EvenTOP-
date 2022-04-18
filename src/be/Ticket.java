package be;

public abstract class Ticket {

    private int id;
    private String barCode;
    private int eventID;

    public Ticket(int id,String barCode,int eventID){
        this.id = id;
        this.barCode = barCode;
        this.eventID = eventID;
    }

    public Ticket(String barCode){
        this.barCode = barCode;
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

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }
}
