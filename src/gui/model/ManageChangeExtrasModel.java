package gui.model;

import bll.BLLFacade;
import bll.BLLManager;
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



}
