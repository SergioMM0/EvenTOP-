package gui.controller;

import be.Event;
import com.jfoenix.controls.JFXComboBox;
import gui.model.EventInfoModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
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

    private EventInfoModel infoModel;
    private EMVController emvController;
    private Event chosenEvent;

    public EventInfoController(){
        infoModel = new EventInfoModel();
        emList = new ListView<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void populateEventInfo(){
        eventName.setText(chosenEvent.getName());
        eventDate.setValue(chosenEvent.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        eventLocation.setText(chosenEvent.getLocation());
        eventInformation.setText(chosenEvent.getInfo());
        startHour.setText(chosenEvent.getStartTime());
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

    public void setChosenEvent(Event event){
        this.chosenEvent = event;
    }


}
