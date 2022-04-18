package be;

public class TicketG extends Ticket {

    private String typeName;
    private String extras;
    private String startTime;
    private String endTime;

    public TicketG(int id, String barCode,int eventId, String typeName, String extras, String startTime, String endTime) {
        super(id, barCode, eventId);
        this.typeName = typeName;
        this.extras = extras;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public TicketG(String barCode, String name, String extras, String startTime, String endTime) {
        super(barCode);
        this.typeName = name;
        this.extras = extras;
        this.startTime = startTime;
        this.endTime = endTime;

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
}
