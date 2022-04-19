package be;

public abstract class Ticket {

    private int id;
    private String barCode;
    private int eventID;
    private String typeName;
    private String extras;
    private String startTime;
    private String endTime;

    public Ticket(int id,String barCode,int eventID){
        this.id = id;
        this.barCode = barCode;
        this.eventID = eventID;
    }

    public Ticket(int id, String barCode, int eventID, String typeName, String extras, String startTime, String endTime) {
        this.id = id;
        this.barCode = barCode;
        this.eventID = eventID;
        this.typeName = typeName;
        this.extras = extras;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Ticket(String barCode,String typeName,String extras, String startTime, String endTime){
        this.barCode = barCode;
        this.typeName = typeName;
        this.extras = extras;
        this.startTime = startTime;
        this.endTime = endTime;
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
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String hour, String min) {
        this.startTime = hour + ":" + min;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String hour, String min) {
        this.endTime = hour + ":" + min;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }
}
