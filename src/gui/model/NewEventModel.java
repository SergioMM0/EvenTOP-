package gui.model;

import be.Event;
import be.User;
import bll.BLLFacade;
import bll.BLLManager;
import dal.exceptions.DALException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class NewEventModel {

    private BLLFacade bllFacade;
    private ObservableList<User> allEms;
    private ObservableList<User> removedEms;

    public NewEventModel(){
        bllFacade = new BLLManager();
        allEms = FXCollections.observableArrayList();
        removedEms = FXCollections.observableArrayList();
    }

    public void addEvent(Event event) throws DALException {
        bllFacade.addEvent(event);
        System.out.println(event);
    }

    public ObservableList<User> getAllEms() throws DALException {
        allEms.clear();
        allEms.addAll(bllFacade.getAllEms());

        return allEms;
    }

    public ObservableList<User> getObservableEms(){
        return allEms;
    }

    public ObservableList<User> getRemovedEms(){
        return removedEms;
    }

    public void addObservableUserCB(User user){
        allEms.add(user);
        removedEms.remove(user);
    }

    public void removeObservableUserCB(User user){
        removedEms.add(user);
        allEms.remove(user);
    }

    public User getUser(String name){
        for(User user : allEms){
            if(user.getName().equals(name)){
                return user;
            }
        }
        return null;
    }

    public User getRemovedUser(String name){
        for(User user : removedEms){
            if(user.getName().equals(name)){
                return user;
            }
        }
        return null;
    }

    public void addEventAndEMs(Event event, List<User> ems) throws DALException{
        bllFacade.addEventAndEMs(event,ems);
    }
}