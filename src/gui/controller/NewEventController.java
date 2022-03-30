package gui.controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class NewEventController implements Initializable {

    private static NewEventController newEventController;

    public NewEventController(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    

    public static NewEventController getInstance() {
        if(newEventController == null){
            newEventController = new NewEventController();
        }
        return newEventController;
    }
}
