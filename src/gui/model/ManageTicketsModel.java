package gui.model;

import be.Ticket;
import bll.BLLFacade;
import bll.BLLManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ManageTicketsModel {

    private BLLFacade bllFacade;
    private ObservableList<Ticket> allTickets;

    public ManageTicketsModel(){
        bllFacade = new BLLManager();
        allTickets = FXCollections.observableArrayList();

    }


}
