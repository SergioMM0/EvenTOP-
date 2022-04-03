package gui.controller;

import be.Event;
import gui.model.NewEventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class NewEventController implements Initializable {

    private NewEventModel newEventModel;

    @FXML
    private DatePicker eventDate;

    @FXML
    private TextField eventInformation;

    @FXML
    private TextField eventLocation;

    @FXML
    private TextField eventName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public NewEventController(){
        newEventModel = new NewEventModel();
    }


    @FXML
    void closeWindow(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void createEventButton(ActionEvent event) {
        Date currentDate = java.sql.Date.valueOf(LocalDate.now());
        Date setDate = null;
        try{
            if(Objects.equals(eventName.getText(), "") || eventName.getText() == null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Login credentials");
                alert.setHeaderText("Introduce a name for the event");
                ButtonType okButton = new ButtonType("OK");
                alert.getButtonTypes().setAll(okButton);
                alert.showAndWait();
            }
            else if(currentDate.compareTo(java.sql.Date.valueOf(eventDate.getValue())) > 0 || eventDate.getValue() == null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Login credentials");
                alert.setHeaderText("Introduce a valid date for the event");
                ButtonType okButton = new ButtonType("OK");
                alert.getButtonTypes().setAll(okButton);
                alert.showAndWait();
            }
            else if(Objects.equals(eventLocation.getText(), "") || eventLocation.getText() == null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Login credentials");
                alert.setHeaderText("Introduce a location for the event");
                ButtonType okButton = new ButtonType("OK");
                alert.getButtonTypes().setAll(okButton);
                alert.showAndWait();
            }
            //To implement when an EM is not assigned to an event.
            else{
                setDate = java.sql.Date.valueOf(eventDate.getValue());
                addEvent();
                closeWindow();
            }
        }catch (NullPointerException ex){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Login credentials");
            alert.setHeaderText("Introduce a valid date for the event");
            ButtonType okButton = new ButtonType("OK");
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
        }
    }

    private void addEvent(){
        newEventModel.addEvent(new Event(eventName.getText(),java.sql.Date.valueOf(eventDate.getValue()),eventLocation.getText(),eventInformation.getText()));
    }

    private void closeWindow(){
        Stage st = (Stage) eventName.getScene().getWindow();
        st.close();
    }

}
