package gui.model;

import be.Event;
import bll.BLLFacade;
import bll.BLLManager;
import javafx.collections.ObservableList;

public class CreateTicketModel {

    private BLLFacade bllFacade;
    private ObservableList<String> allTypes;
    private ObservableList<String> allExtras;

    public CreateTicketModel(){
        bllFacade = new BLLManager();
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

    public ObservableList<String> getAllExtras(){
        return allExtras;
    }

    public ObservableList<String> getAllTypes(){
        return allTypes;
    }


}
