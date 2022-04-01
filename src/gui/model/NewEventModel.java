package gui.model;

import be.Event;
import bll.BLLFacade;
import bll.BLLManager;
import gui.controller.NewEventController;

public class NewEventModel {

    private BLLFacade bllFacade;

    public NewEventModel(){
        bllFacade = new BLLManager();
    }

    public void addEvent(Event event){
        bllFacade.addEvent(event);
        System.out.println(event);
    }
}
