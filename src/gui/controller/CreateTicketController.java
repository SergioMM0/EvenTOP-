package gui.controller;

import be.Event;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dal.exceptions.DALException;
import gui.model.CreateTicketModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateTicketController implements Initializable {

    @FXML
    private CheckBox addCustomerInfoCheckBox;

    @FXML
    private JFXButton addExtraButton;

    @FXML
    private JFXButton addTicketButton;

    @FXML
    private TextField assistHour;

    @FXML
    private TextField assistMin;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private TextField customerEmail;

    @FXML
    private TextField customerName;

    @FXML
    private TextField customerPhone;

    @FXML
    private JFXButton deleteExtraButton;

    @FXML
    private JFXComboBox<String> extrasComboBox;

    @FXML
    private ListView<String> extrasList;

    @FXML
    private TextField leaveHour;

    @FXML
    private TextField leaveMin;

    @FXML
    private JFXButton manageExtrasButton;

    @FXML
    private JFXButton manageTicketTypesButton;

    @FXML
    private TextField rowNumber;

    @FXML
    private CheckBox rowsSeatCheckBox;

    @FXML
    private TextField seatNumber;

    @FXML
    private JFXComboBox<String> ticketTypeComboBox;

    private CreateTicketModel createTicketModel;
    private Event chosenEvent;

    public CreateTicketController(){
        createTicketModel = new CreateTicketModel();
    }

    @FXML
    void addExtra(ActionEvent event) {
        createTicketModel.addExtraToTicket(extrasComboBox.getValue());
        repopulateExtrasComboBox();
        repopulateExtrasList();
    }

    @FXML
    void deleteExtra(ActionEvent event) {
        createTicketModel.removeExtraToTicket(extrasList.getSelectionModel().getSelectedItem());
        repopulateExtrasList();
        repopulateExtrasComboBox();
    }

    @FXML
    void addTicket(ActionEvent event) {

    }

    @FXML
    void cancelCreatingTicket(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void openManageExtrasView(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("gui/view/ManageExtrasView.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ManageExtrasController manageExtrasController = loader.getController();
            manageExtrasController.setController(this);
            Stage stage = new Stage();
            stage.setTitle("Extras of the ticket");
            assert root != null;
            stage.setScene(new Scene(root, 400, 300));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openManageTicketTypesView(ActionEvent event) {

    }

    @FXML
    void ticketTypeComboBox(ActionEvent event) {

    }

    private boolean timeIsCorrect(){
        if(Integer.parseInt(assistHour.getText())>23 || Integer.parseInt(assistHour.getText()) < 0){
            return false;
        }
        else if(Integer.parseInt(assistMin.getText())>59 || Integer.parseInt(assistMin.getText()) < 0 ){
            return false;
        }
        else return true;
    }

    public void setChosenEvent(Event chosenEvent) {
        this.chosenEvent = chosenEvent;
    }

    public void setExtras(ObservableList<String> observableExtras) {
        createTicketModel.setNewExtras(observableExtras);
    }

    public void populateExtrasComboBox(){
        try{
            extrasComboBox.getItems().addAll(createTicketModel.getAllExtras(chosenEvent));
        }catch(DALException dalException){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Something gone wrong");
            alert.setHeaderText(dalException.getMessage());
            ButtonType okButton = new ButtonType("OK");
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
        }
    }

    public void repopulateExtrasComboBox() {
        extrasComboBox.getItems().clear();
        extrasComboBox.getItems().addAll(createTicketModel.getObservableExtras());
    }

    public void repopulateExtrasList(){
        extrasList.getItems().clear();
        extrasList.getItems().addAll(createTicketModel.getExtrasInEvent());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void initializeView(){
        populateExtrasComboBox();
        populateTypes();
    }

    private void closeWindow(){
        Stage st = (Stage) cancelButton.getScene().getWindow();
        st.close();
    }

    public void populateTypes(){
        try{
            ticketTypeComboBox.getItems().addAll(createTicketModel.getAllTypesForEvent(chosenEvent));
        }catch(DALException dal){
            dal.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Something gone wrong");
            alert.setHeaderText(dal.getMessage());
            ButtonType okButton = new ButtonType("OK");
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
        }

    }
}

