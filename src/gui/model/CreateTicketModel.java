package gui.model;

import be.Event;
import be.TicketG;
import be.TicketRS;
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

    public ObservableList<String> getObservableTypes(){
        return allTypes;
    }

    public void addTypesToList(String[] types){
        for (int i = 0; i < types.length-1; i++) {//to test with the length -1
            allTypes.add(i,types[i]);
        }
    }

    public void addExtrasToList(String[] extras){
        for (int i = 0; i < extras.length-1; i++) {
            allExtras.add(i,extras[i]);
        }
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
}
