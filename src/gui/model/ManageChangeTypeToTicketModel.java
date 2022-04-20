package gui.model;

import be.Event;
import be.TicketG;
import bll.BLLFacade;
import bll.BLLManager;
import dal.exceptions.DALException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManageChangeTypeToTicketModel {

    private BLLFacade bllFacade;
    private ObservableList<String> allTypes;
    private TicketG chosenTicket;

    public ManageChangeTypeToTicketModel(){
        bllFacade = new BLLManager();
        allTypes = FXCollections.observableArrayList();
    }

    public ObservableList<String> getAllTypesInEvent(Event event) throws DALException {
        allTypes.addAll(bllFacade.getAllTypesForEvent(event));
        return allTypes;
    }

    public void addNewType(String text) {
        allTypes.add(text);
    }


    public ObservableList<String> getAllObservableTypes() {
        return allTypes;
    }

    public void updateTicket(TicketG chosenTicket) throws DALException {
        //TO IMPLEMENT
    }
}
