package gui.model;

import be.TicketG;
import bll.BLLFacade;
import bll.BLLManager;
import dal.exceptions.DALException;

public class ManageChangeAssistLeaveModel {

    private BLLFacade bllFacade;

    public ManageChangeAssistLeaveModel(){
        bllFacade = new BLLManager();
    }

    public void updateTicketTime(TicketG chosenTicket)throws DALException {
        bllFacade.updateAssistLeaveTime(chosenTicket);
    }
}
