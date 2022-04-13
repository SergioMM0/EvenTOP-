package gui.model;

import be.Event;
import be.User;
import bll.BLLFacade;
import bll.BLLManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EventInfoModel {

    private BLLFacade bllFacade;
    private ObservableList<User> allEms;
    private ObservableList<User> removedEms;
    private Event chosenEvent = null;

    public EventInfoModel(){
        bllFacade = new BLLManager();
        allEms = FXCollections.observableArrayList();
        removedEms = FXCollections.observableArrayList();
    }


    public void setSelectedEvent(Event chosenEvent) {
        this.chosenEvent = chosenEvent;
    }

    public Event getChosenEvent(){
        return chosenEvent;
    }
}
