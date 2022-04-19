package gui.controllers;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
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
    private static final String errTitle = "Something went wrong";

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
        populateEmComboBox();
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
        User selected = eventInfoModel.getEmNotInEvent(emComboBox.getValue());
        eventInfoModel.addEmInCharge(selected);
        repopulateEmComboBox();
        repopulateEmList();
    }

    private void repopulateEmComboBox() {
        emComboBox.getItems().clear();
        for(User user : eventInfoModel.getObservableEmsNotInCharge()) {
            emComboBox.getItems().add(user.getName());
        }
    }

    private void repopulateEmList(){
        emList.getItems().clear();
        for(User user : eventInfoModel.getObservableEmsInCharge()){
            emList.getItems().add(user.getName());
        }
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
        Date currentDate = java.sql.Date.valueOf(LocalDate.now());
        Date setDate = null;
        try {
            if (Objects.equals(eventName.getText(), "") || eventName.getText() == null) {
                throwAlert(errTitle,"Introduce a valid name for the event");
            } else if (currentDate.compareTo(java.sql.Date.valueOf(eventDate.getValue())) > 0 || eventDate.getValue() == null) {
                throwAlert(errTitle,"Introduce a valid date for the event");
            } else if (Objects.equals(eventLocation.getText(), "") || eventLocation.getText() == null) {
                throwAlert(errTitle,"Introduce a location for the event");
            } else if (!timeIsCorrect()){
                throwAlert(errTitle,"Introduce a valid start time");
            }
            else {
                setDate = java.sql.Date.valueOf(eventDate.getValue());
                try {
                    editEvent();
                } catch (DALException dalException) {
                    throwAlert(errTitle,dalException.getMessage());
                }
                closeWindow();
            }
        } catch (NullPointerException ex) {
            throwAlert(errTitle,"Introduce a valid date for the event");
        }
    }

    private boolean timeIsCorrect(){
        if(Integer.parseInt(startHour.getText())>23 || Integer.parseInt(startHour.getText()) < 0){
            return false;
        }
        else if(Integer.parseInt(startMin.getText())>59 || Integer.parseInt(startMin.getText()) < 0 ){
            return false;
        }//is optional to add end hour
        try{
            Integer.parseInt(startHour.getText());
            Integer.parseInt(startMin.getText());
            Integer.parseInt(endHour.getText());
            Integer.parseInt(endMin.getText());
        }catch (NumberFormatException numberFormatException){
            return false;
        }
        return true;
    }

    public void editEvent()throws DALException{
            eventInfoModel.updateEventAndEms(
                    new Event(
                            eventInfoModel.getChosenEvent().getId(),
                            eventName.getText(),
                            java.sql.Date.valueOf(eventDate.getValue()),
                            eventLocation.getText(),
                            eventInformation.getText(),
                            startHour.getText() + ":" + startMin.getText(),
                            endHour.getText() + ":" + endMin.getText()
                    ), eventInfoModel.getObservableEmsInCharge());
        closeWindow();
        emvController.repopulateEventsTable();
    }

    @FXML
    void deleteEM(ActionEvent event) {
        User selected = eventInfoModel.getEmInEvent(emList.getSelectionModel().getSelectedItem());
        eventInfoModel.removeEmInCharge(selected);
        repopulateEmList();
        repopulateEmComboBox();
    }

    private void closeWindow(){
        Stage st = (Stage) eventName.getScene().getWindow();
        st.close();
    }

    public void throwAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        ButtonType okButton = new ButtonType("OK");
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
    }

    public void setController(EMVController emvController) {
        this.emvController = emvController;
    }
}

