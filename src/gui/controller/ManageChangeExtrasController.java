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
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
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
        if(extrasAvailableListView.getSelectionModel().getSelectedItem() == null){
           throwAlert("Please select a extra");
        }else{
            manageChangeExtrasModel.addExtraToTicket(extrasAvailableListView.getSelectionModel().getSelectedItem());
            repopulateExtrasAvailable();
            repopulateExtrasOnTicket();
        }
    }

    private void repopulateExtrasOnTicket() {
        extrasOnTicketListView.getItems().clear();
        extrasOnTicketListView.getItems().addAll(manageChangeExtrasModel.getObservableExtras());
    }

    private void repopulateExtrasAvailable() {
        extrasAvailableListView.getItems().clear();
        extrasAvailableListView.getItems().addAll(manageChangeExtrasModel.getExtrasAvailable());
    }

    @FXML
    void addNewExtra(ActionEvent event) {
        if(newExtraField.getText().isEmpty()){
            throwAlert("Please introduce a valid extra for the ticket");
        }else{
            manageChangeExtrasModel.addObservableExtra(newExtraField.getText());
            newExtraField.clear();
            repopulateExtrasAvailable();
            repopulateExtrasOnTicket();
        }
    }

    @FXML
    void cancelEditing(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void removeExtraFromTicket(ActionEvent event) {
        if((extrasOnTicketListView.getSelectionModel().getSelectedItem() == null)){
            throwAlert("Please select a extra to remove");
        }
        else{
            manageChangeExtrasModel.removeExtraFromTicket(extrasOnTicketListView.getSelectionModel().getSelectedItem());
            repopulateExtrasOnTicket();
            repopulateExtrasAvailable();
        }
    }

    @FXML
    void saveChanges(ActionEvent event) {
        try{
            chosenTicket.setExtras(getExtrasInEventAsString());
            manageChangeExtrasModel.updateExtrasFromTicket(chosenTicket);
            manageTicketsController.repopulateTicketsView();
            closeWindow();
        }catch (DALException dalException){
            throwAlert(dalException.getMessage());
        }
    }

    public String getExtrasInEventAsString(){
        List<String> allExtras = manageChangeExtrasModel.getObservableExtras();
        if(allExtras.size() == 0){
            return "No extras";
        }
        StringBuilder all = new StringBuilder();
        for (int i = 0; i < allExtras.size()-1; i++) {
            all.append(allExtras.get(i)).append(", ");
        }
        return all.toString();
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
            if(!s.equals("No extras")){
                manageChangeExtrasModel.addObservableExtra(s);
            }
        }
        extrasOnTicketListView.getItems().addAll(manageChangeExtrasModel.getObservableExtras());
    }

    private void closeWindow(){
        Stage st = (Stage) cancelButton.getScene().getWindow();
        st.close();
    }
}