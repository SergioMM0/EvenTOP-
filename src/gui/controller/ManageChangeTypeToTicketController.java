package gui.controller;

import be.Event;
import be.TicketG;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dal.exceptions.DALException;
import gui.model.ManageChangeTypeToTicketModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageChangeTypeToTicketController implements Initializable {

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private TextField newTypeField;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXComboBox<String> typesComboBox;

    private Event chosenEvent;
    private TicketG chosenTicket;
    private ManageTicketsController manageTicketsController;
    private ManageChangeTypeToTicketModel model;
    private static final String errTitle = "Something went wrong";

    @FXML
    void addNewType(ActionEvent event) {
        if(newTypeField.getText().isEmpty()){
            throwAlert("Introduce a valid type");
        }else{
            model.addNewType(newTypeField.getText());
            repopulateComboBox();
            newTypeField.clear();
        }
    }

    public void populateComboBox(Event chosenEvent){
        try{
            typesComboBox.getItems().addAll(model.getAllTypesInEvent(this.chosenEvent));
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

    private void repopulateComboBox() {
        typesComboBox.getItems().clear();
        typesComboBox.getItems().addAll(model.getAllObservableTypes());
    }

    @FXML
    void cancelEditing(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void updateTicketType(ActionEvent event) {
        if(!typesComboBox.getSelectionModel().isEmpty()){
            try{
                chosenTicket.setTypeName(typesComboBox.getSelectionModel().getSelectedItem());
                model.updateTicket(chosenTicket);
            }catch (DALException dalException){
                dalException.printStackTrace();
                throwAlert(dalException.getMessage());
            }
        }else throwAlert("Please select a type for the ticket");
        manageTicketsController.repopulateTicketsView();
        closeWindow();
    }

    public void setChosenEvent(Event chosenEvent) {
        this.chosenEvent = chosenEvent;
    }

    public void setChosenTicket(TicketG ticket){
        this.chosenTicket = ticket;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new ManageChangeTypeToTicketModel();
    }

    private void closeWindow() {
        Stage st = (Stage) cancelButton.getScene().getWindow();
        st.close();
    }

    public void setController(ManageTicketsController manageTicketsController) {
        this.manageTicketsController = manageTicketsController;
    }
}

