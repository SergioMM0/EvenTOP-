package gui.controller;

import be.Event;
import be.User;
import com.jfoenix.controls.JFXComboBox;
import dal.exceptions.DALException;
import gui.model.EventInfoModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class EventInfoController implements Initializable {

    @FXML
    private JFXComboBox<String> emComboBox;

    @FXML
    private ListView<String> emList;

    @FXML
    private TextField endHour;

    @FXML
    private TextField endMin;

    @FXML
    private DatePicker eventDate;

    @FXML
    private TextField eventInformation;

    @FXML
    private TextField eventLocation;

    @FXML
    private TextField eventName;

    @FXML
    private TextField startHour;

    @FXML
    private TextField startMin;

    private EventInfoModel eventInfoModel;
    private EMVController emvController;
    private Event chosenEvent;

    public EventInfoController(){
        eventInfoModel = new EventInfoModel();
        emList = new ListView<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void populateEventInfo(Event event){
        eventName.setText(event.getName());
        eventDate.setValue(Instant.ofEpochMilli(event.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
        eventLocation.setText(event.getLocation());
        eventInformation.setText(event.getInfo());
        String[] start = event.getStartTime().split(":");
        String[] end = event.getEndTime().split(":");
        startHour.setText(start[0]);
        startMin.setText(start[1]);
        endHour.setText(end[0]);
        endMin.setText(end[1]);
        eventInfoModel.setChosenEvent(event);
        populateEmList();
    }

    public void populateEmList(){
        try{
            for(User user : eventInfoModel.getEmsInEvent()){
                emList.getItems().add(user.getName());
            }
        }catch(DALException dalException){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Database error");
            alert.setHeaderText(dalException.getMessage());
            ButtonType okButton = new ButtonType("OK");
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
        }
    }

    public void populateEmComboBox(){
        try{
            for(User user : eventInfoModel.getEmsNotInEvent()){
                emComboBox.getItems().add(user.getName());
            }
        }catch (DALException dalException){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Database error");
            alert.setHeaderText(dalException.getMessage());
            ButtonType okButton = new ButtonType("OK");
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
        }
    }

    @FXML
    void addEM(ActionEvent event) {

    }

    @FXML
    void DeleteEvent(ActionEvent event) {

    }

    @FXML
    void closeWindow(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void editEvent(ActionEvent event) {

    }

    @FXML
    void deleteEM(ActionEvent event) {

    }

    private void closeWindow(){
        Stage st = (Stage) eventName.getScene().getWindow();
        st.close();
    }

    public void setController(EMVController emvController) {
        this.emvController = emvController;
    }
}

