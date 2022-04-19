package be;

public class TicketG extends Ticket {

    public TicketG(int id, String barCode,int eventId, String typeName, String extras, String startTime, String endTime) {
        super(id, barCode, eventId,typeName,extras,startTime,endTime);
    }
    public TicketG(String barCode,String typeName,String extras, String startTime, String endTime){
        super(barCode,typeName,extras,startTime,endTime);
    }
}
