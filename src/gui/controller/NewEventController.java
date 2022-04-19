package gui.controller;

import be.Event;
import be.User;
import com.jfoenix.controls.JFXComboBox;
import dal.exceptions.DALException;
import gui.model.NewEventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class NewEventController implements Initializable {

    @FXML
    private JFXComboBox<String> emComboBox;

    @FXML
    private DatePicker eventDate;

    @FXML
    private TextField eventInformation;

    @FXML
    private TextField eventLocation;

    @FXML
    private TextField eventName;

    @FXML
    private ListView<String> emList;

    @FXML
    private TextField endHour;

    @FXML
    private TextField endMin;

    @FXML
    private TextField startHour;

    @FXML
    private TextField startMin;

    private NewEventModel newEventModel;
    private EMVController emvController;
    private static final String errTitle = "Something went wrong";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateComboBox();
    }

    public NewEventController() {
        newEventModel = new NewEventModel();
        emList = new ListView<>();
    }

    @FXML
    void addEM(ActionEvent event) {
        User selected = newEventModel.getUser(emComboBox.getValue());
        emList.getItems().add(selected.getName());
        newEventModel.removeObservableUserCB(selected);
        repopulateComboBox();
    }

    private void repopulateComboBox() {
        emComboBox.getItems().clear();
        for (User user : newEventModel.getObservableEms()) {
            emComboBox.getItems().add(user.getName());
        }
    }

    public void populateComboBox() {
        try {
            for (User user : newEventModel.getAllEms()) {
                emComboBox.getItems().add(user.getName());
            }
        } catch (DALException dalException) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Database error");
            alert.setHeaderText(dalException.getMessage());
            ButtonType okButton = new ButtonType("OK");
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
        }
    }

    @FXML
    void deleteEM(ActionEvent event) {
        String selectedInCB = emList.getSelectionModel().getSelectedItem();
        newEventModel.addObservableUserCB(newEventModel.getRemovedUser(selectedInCB));
        repopulateComboBox();
        repopulateEmList();
    }

    private void repopulateEmList() {
        emList.getItems().clear();
        for (User user : newEventModel.getRemovedEms()) {
            emList.getItems().add(user.getName());
        }
    }

    @FXML
    void closeWindow(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void createEventButton(ActionEvent event) {
        Date currentDate = java.sql.Date.valueOf(LocalDate.now());
        Date setDate = null;
        try {
            if (Objects.equals(eventName.getText(), "") || eventName.getText() == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Creating event");
                alert.setHeaderText("Introduce a name for the event");
                ButtonType okButton = new ButtonType("OK");
                alert.getButtonTypes().setAll(okButton);
                alert.showAndWait();
            } else if (currentDate.compareTo(java.sql.Date.valueOf(eventDate.getValue())) > 0 || eventDate.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Creating event");
                alert.setHeaderText("Introduce a valid date for the event");
                ButtonType okButton = new ButtonType("OK");
                alert.getButtonTypes().setAll(okButton);
                alert.showAndWait();
            } else if (Objects.equals(eventLocation.getText(), "") || eventLocation.getText() == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Creating event");
                alert.setHeaderText("Introduce a location for the event");
                ButtonType okButton = new ButtonType("OK");
                alert.getButtonTypes().setAll(okButton);
                alert.showAndWait();
            } else if (!timeIsCorrect()){
                throwAlert(errTitle,"Introduce a valid start time");
            }
            else {
                setDate = java.sql.Date.valueOf(eventDate.getValue());
                try {
                    addEvent();
                } catch (DALException dalException) {
                    throwAlert("Something went wrong",dalException.getMessage());
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
        }
        else return true;
    }

    private void addEvent() throws DALException {
        if (newEventModel.getRemovedEms().isEmpty()) {
            newEventModel.addEvent(
                    new Event(0,
                            eventName.getText(),
                            java.sql.Date.valueOf(eventDate.getValue()),
                            eventLocation.getText(),
                            eventInformation.getText(),
                            startHour.getText() + ":" + startMin.getText(),
                            endHour.getText()+ ":" +endMin.getText()
                            ));
        } else {
            newEventModel.addEventAndEMs(
                    new Event(0,
                            eventName.getText(),
                            java.sql.Date.valueOf(eventDate.getValue()),
                            eventLocation.getText(),
                            eventInformation.getText(),
                            startHour.getText()+ ":" +startMin.getText(),
                            endHour.getText()+ ":" +endMin.getText()
                            ),newEventModel.getRemovedEms());
        }
        emvController.repopulateEventsTable();
    }

    private void closeWindow() {
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
