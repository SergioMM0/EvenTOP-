package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EMVController implements Initializable {

    public static EMVController emvController;

    public EMVController(){

    }

    @FXML
    void openNewEventView(ActionEvent event) {
        openNewEventWindow();
    }

    private void openNewEventWindow(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("gui/view/NewEventView.fxml"));
        Parent root = null;
        try{root = loader.load();}
        catch (IOException conn){}
        assert root != null;
        root.getStylesheets().add("");  //CSS after
        loader.setController(NewEventController.getInstance());
        Stage stage = new Stage();
        stage.setTitle("EVENTOP");
        stage.setScene(new Scene(root,600,420));
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public static EMVController getInstance(){
        if(emvController == null){
            emvController = new EMVController();
        }
        return emvController;
    }
}
