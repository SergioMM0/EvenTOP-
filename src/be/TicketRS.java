package be;

public class TicketRS extends Ticket {

    private String typeName;
    private String extras;
    private String startTime;
    private String endTime;
    private int row;
    private int seat;

    public TicketRS(int id, String barCode,int eventId, String name, String extras, String startTime, String endTime, int row, int seat) {
        super(id, barCode, eventId);
        this.typeName = name;
        this.extras = extras;
        this.startTime = startTime;
        this.endTime = endTime;
        this.row = row;
        this.seat = seat;
    }

    public TicketRS(String barCode, String name, String extras, String startTime, String endTime, int row, int seat) {
        super(barCode);
        this.typeName = name;
        this.extras = extras;
        this.startTime = startTime;
        this.endTime = endTime;
        this.row = row;
        this.seat = seat;
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

    public void setStartTime(String hour, String min) {
        this.startTime = hour + ":" + min;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String hour, String min) {
        this.endTime = hour + ":" + min;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
}
