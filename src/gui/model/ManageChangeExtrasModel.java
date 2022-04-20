package gui.model;

import be.Event;
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
        return extrasOnEvent;
    }

    public void addObservableExtra(String s) {
        extrasOnTicket.add(s);
    }

    public ObservableList<String> getObservableExtras() {
        return extrasOnTicket;
    }


}
