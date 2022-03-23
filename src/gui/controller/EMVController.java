package gui.controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class EMVController implements Initializable {

    public static EMVController emvController;

    public EMVController(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public EMVController getInstance(){
        if(emvController == null){
            emvController = new EMVController();
        }
        return emvController;
    }
}
