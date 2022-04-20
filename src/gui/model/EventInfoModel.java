package gui.model;

import be.Event;
import be.User;
import bll.BLLFacade;
import bll.BLLManager;
import dal.exceptions.DALException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class EventInfoModel {

    private BLLFacade bllFacade;
    private ObservableList<User> emsNotInEvent;
    private ObservableList<User> emsInEvent;
    private Event chosenEvent;

    public EventInfoModel(){
        bllFacade = new BLLManager();
        emsNotInEvent = FXCollections.observableArrayList();
        emsInEvent = FXCollections.observableArrayList();
    }

    public void setChosenEvent(Event event){
        this.chosenEvent = event;

    }

    public Event getChosenEvent(){
        return chosenEvent;
    }

    public ObservableList<User> getEmsInEvent() throws DALException {
        emsInEvent.addAll(bllFacade.getEmsInEvent(chosenEvent));
        return emsInEvent;
    }

    public ObservableList<User> getEmsNotInEvent() throws DALException{
        emsNotInEvent.addAll(bllFacade.getEmsNotInEvent(chosenEvent));
        return emsNotInEvent;
    }

    public ObservableList<User> getObservableEmsInCharge(){
        return emsInEvent;
    }

    public ObservableList<User> getObservableEmsNotInCharge(){
        return emsNotInEvent;
    }

    public User getEmInEvent(String name){
        for(User user : emsInEvent){
            if(user.getName().equals(name)){
                return user;
            }
        }
        return null;
    }

    public User getEmNotInEvent(String name){
        for(User user : emsNotInEvent){
            if(user.getName().equals(name)){
                return user;
            }
        }
        return null;
    }

    public void addEmInCharge(User user){
        emsInEvent.add(user);
        emsNotInEvent.remove(user);
    }

    public void removeEmInCharge(User user){
        emsNotInEvent.add(user);
        emsInEvent.remove(user);
    }

    public void updateEventAndEms(Event event, List<User> ems) throws DALException{
        //event.setId(chosenEvent.getId());
        bllFacade.updateEventAndEms(event,ems);
    }

    public void deleteChosenEvent(Event chosenEvent) throws DALException{
        bllFacade.deleteEvent(chosenEvent);
    }
}
