package gui.controller;

import be.Event;
import be.TicketG;
import com.jfoenix.controls.JFXButton;
import dal.exceptions.DALException;
import gui.model.ManageChangeExtrasModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageChangeExtrasController implements Initializable {

    @FXML
    private JFXButton addExtraToTicketButton;

    @FXML
    private JFXButton addNewExtraButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private ListView<String> extrasAvailableListView;

    @FXML
    private ListView<String> extrasOnTicketListView;

    @FXML
    private TextField newExtraField;

    @FXML
    private JFXButton removeButton;

    @FXML
    private JFXButton saveButton;
    private ManageTicketsController manageTicketsController;
    private ManageChangeExtrasModel manageChangeExtrasModel;
    private Event chosenEvent;
    private TicketG chosenTicket;
    private static final String errTitle = "Something went wrong";

    @FXML
    void addExtraToTicket(ActionEvent event) {

    }

    @FXML
    void addNewExtra(ActionEvent event) {

    }

    @FXML
    void cancelEditing(ActionEvent event) {

    }

    @FXML
    void removeExtraFromTicket(ActionEvent event) {

    }

    @FXML
    void saveChanges(ActionEvent event) {

    }

    public void setController(ManageTicketsController manageTicketsController){
        this.manageTicketsController = manageTicketsController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        manageChangeExtrasModel = new ManageChangeExtrasModel();
    }

    public void setChosenEvent(Event chosenEvent) {
        this.chosenEvent = chosenEvent;
    }

    public void setChosenTicket(TicketG ticket){
        this.chosenTicket = ticket;
    }

    public void populateAllExtras() {
        try{
            extrasAvailableListView.getItems().addAll(manageChangeExtrasModel.getAllExtrasInEvent(chosenEvent));
        }catch (DALException dalException){
            throwAlert(dalException.getMessage());
        }
    }
    private void throwAlert( String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(errTitle);
        alert.setHeaderText(message);
        ButtonType okButton = new ButtonType("OK");
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
    }

    public void populateExtrasOnEvent() {
        String[] raw = chosenTicket.getExtras().split(", ");
        for (String s : raw) {
            manageChangeExtrasModel.addObservableExtra(s);
        }
        extrasOnTicketListView.getItems().addAll(manageChangeExtrasModel.getObservableExtras());
    }
}