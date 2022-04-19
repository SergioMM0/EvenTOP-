package bll;

import be.Event;
import be.TicketG;
import be.TicketRS;

public class HourFixer {

    public static HourFixer hourFixer;

    public HourFixer(){}

    public static HourFixer getInstance(){
        if(hourFixer == null){
            hourFixer = new HourFixer();
        }
        return hourFixer;
    }

    public void fixStartHour(Event event){
        String[] wrong = event.getStartTime().split(":");
        parseAndCompare(wrong);
        event.setStartTime(wrong[0],wrong[1]);
    }

    public void fixEndHour(Event event){
        String[] wrong = event.getEndTime().split(":");
        parseAndCompare(wrong);
        event.setEndTime(wrong[0],wrong[1]);
    }

    private void parseAndCompare(String[] wrong) {
        if(Integer.parseInt(wrong[0]) < 10 && wrong[0].length() == 1){
            wrong[0] = "0"+wrong[0];
        }
        if (Integer.parseInt(wrong[1]) < 10 && wrong[1].length() == 1){
            wrong[1] = "0"+ wrong[1];
        }
    }

    public void fixTicketGAssist(TicketG ticketG){
        String[] wrong = ticketG.getStartTime().split(":");
        parseAndCompare(wrong);
        ticketG.setStartTime(wrong[0],wrong[1]);
    }

    public void fixTicketRSAssist(TicketRS ticketRS){
        String[] wrong = ticketRS.getStartTime().split(":");
        parseAndCompare(wrong);
        ticketRS.setStartTime(wrong[0],wrong[1]);
    }

    public void fixTicketGLeave(TicketG ticketG){
        String[] wrong = ticketG.getEndTime().split(":");
        parseAndCompare(wrong);
        ticketG.setEndTime(wrong[0],wrong[1]);
    }

    public void fixTicketRSLeave(TicketRS ticketRS){
        String[] wrong = ticketRS.getEndTime().split(":");
        parseAndCompare(wrong);
        ticketRS.setEndTime(wrong[0],wrong[1]);
    }
}
