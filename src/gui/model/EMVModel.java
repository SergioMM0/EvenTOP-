package gui.model;

import be.Event;
import bll.BLLFacade;
import bll.BLLManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EMVModel {

    private BLLFacade bllFacade;
    private ObservableList<Event> events;

    public EMVModel(){
        bllFacade = new BLLManager();
        events = FXCollections.observableArrayList();
    }

    public void getAllEvents() {

    }

}
