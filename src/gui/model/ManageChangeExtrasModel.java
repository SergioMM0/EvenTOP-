package gui.model;

import be.Event;
import be.TicketG;
import bll.BLLFacade;
import bll.BLLManager;
import dal.exceptions.DALException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManageChangeExtrasModel {

    private BLLFacade bllFacade;
    private ObservableList<String> extrasOnEvent;
    private ObservableList<String> extrasOnTicket;

    public ManageChangeExtrasModel(){
        bllFacade = new BLLManager();
        extrasOnEvent = FXCollections.observableArrayList();
        extrasOnTicket = FXCollections.observableArrayList();
    }
    public ObservableList<String> getAllExtrasInEvent(Event chosenEvent) throws DALException {
        extrasOnEvent.addAll(bllFacade.getAllExtrasForEvent(chosenEvent));
        extrasOnEvent.remove("No extras");
        return extrasOnEvent;
    }

    public void addObservableExtra(String s) {
        extrasOnTicket.add(s);
    }

    public void addExtraToTicket(String string){
        extrasOnTicket.add(string);
        extrasOnEvent.remove(string);
    }

    public void removeExtraFromTicket(String string){
        extrasOnTicket.remove(string);
        extrasOnEvent.add(string);
    }

    public ObservableList<String> getObservableExtras() {
        return extrasOnTicket;
    }

    public ObservableList<String> getExtrasAvailable(){
        return extrasOnEvent;
    }

    public void updateExtrasFromTicket(TicketG chosenTicket) throws DALException{
        bllFacade.updateExtrasFromTicket(chosenTicket);
    }
}
