package gui.model;

import be.TicketG;
import bll.BLLFacade;
import bll.BLLManager;
import dal.exceptions.DALException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManageTicketsModel {

    private BLLFacade bllFacade;
    private ObservableList<TicketG> allTickets;

    public ManageTicketsModel(){
        bllFacade = new BLLManager();
        allTickets = FXCollections.observableArrayList();
    }

    public ObservableList<TicketG> getAllTickets() throws DALException {
        allTickets.addAll(bllFacade.getAllTickets());
        return allTickets;
    }

}
