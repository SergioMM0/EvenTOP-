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

    public EMVController(){
    }

    @FXML
    void openNewEventView(ActionEvent event) {
        openNewEventWindow();
    }

    private void openNewEventWindow(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/view/NewEventView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("EvenTOP");
            stage.setScene(new Scene(root, 600, 420));
            stage.setResizable(false);
            //root.getStylesheets().add("");  //CSS after
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void openEventInfo(ActionEvent event) {

    }
}
