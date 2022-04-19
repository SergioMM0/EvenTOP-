package gui.model;

import be.Event;
import be.TicketG;
import be.TicketRS;
import be.User;
import bll.BLLFacade;
import bll.BLLManager;
import dal.exceptions.DALException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CreateTicketModel {

    private BLLFacade bllFacade;
    private ObservableList<String> allTypes;
    private ObservableList<String> allExtras;
    private ObservableList<String> extrasOnEvent;

    public CreateTicketModel(){
        bllFacade = new BLLManager();
        allExtras = FXCollections.observableArrayList();
        allTypes = FXCollections.observableArrayList();
        extrasOnEvent = FXCollections.observableArrayList();
    }

    public ObservableList<String> getObservableExtras(){
        return allExtras;
    }

    public void setNewExtras(ObservableList<String> observableExtras) {
        allExtras.addAll(observableExtras);
    }

    public ObservableList<String> getAllExtras(Event chosenEvent) throws DALException {
        allExtras.clear();
        allExtras.addAll(bllFacade.getAllExtrasForEvent(chosenEvent));
        return allExtras;
    }

    public String getExtrasInEventAsString(){
        if(extrasOnEvent.size() == 0){
            return "No extras";
        }
        StringBuilder all = new StringBuilder();
        for (int i = 0; i < extrasOnEvent.size()-1; i++) {
            all.append(extrasOnEvent.get(i)).append(", ");
        }
        return all.toString();
    }

    public void addExtraToTicket(String value) {
        extrasOnEvent.add(value);
        allExtras.remove(value);
    }

    public void removeExtraToTicket(String value){
        allExtras.add(value);
        extrasOnEvent.remove(value);
    }

    public ObservableList<String> getExtrasInEvent() {
        return extrasOnEvent;
    }

    public ObservableList<String> getAllTypesForEvent(Event chosenEvent) throws DALException{
        allTypes.addAll(bllFacade.getAllTypesForEvent(chosenEvent));
        return allTypes;
    }

    public void addTypeToList(String text) {
        allTypes.add(text);
    }

    public ObservableList<String> getAllObservableTypes() {
        return allTypes;
    }

    public void addTicketG(TicketG ticketG, Event event) throws DALException{
        bllFacade.addTicketG(ticketG,event);
    }

    public void addTicketRS(TicketRS ticketRS, Event event) throws DALException{
        bllFacade.addTicketRS(ticketRS,event);
    }

    public void addTicketRSAndUser(TicketRS ticketRS,Event event,User user) throws DALException{
        bllFacade.addTicketRSAndUser(ticketRS,event,user);
    }

    public void addTicketGAndUser(TicketG ticketG,Event event,User user) throws DALException{
        bllFacade.addTicketGAndUser(ticketG,event,user);
    }
}
