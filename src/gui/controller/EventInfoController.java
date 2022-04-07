package gui.controller;

import com.jfoenix.controls.JFXComboBox;
import gui.model.NewEventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
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

    private NewEventModel eventModel;

    public EventInfoController(){
        eventModel = new NewEventModel();
        emList = new ListView<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void addEM(ActionEvent event) {

    }

    @FXML
    void closeWindow(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void createEventButton(ActionEvent event) {

    }

    @FXML
    void deleteEM(ActionEvent event) {

    }

    private void closeWindow(){
        Stage st = (Stage) eventName.getScene().getWindow();
        st.close();
    }
}
