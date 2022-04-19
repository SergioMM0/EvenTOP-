package be;

public class TicketRS extends Ticket {

    private int row;
    private int seat;

    public TicketRS(String barCode, String typeName,String extras, String startTime, String endTime, int row, int seat) {
        super(barCode,typeName,extras,startTime,endTime);
        this.row = row;
        this.seat = seat;
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
