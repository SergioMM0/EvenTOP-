package gui.controller;

import be.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dal.exceptions.DALException;
import gui.model.CreateTicketModel;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.lang.Object;

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
    private boolean rs;
    private boolean ci;

    public CreateTicketController() {
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
        if (!timeIsCorrect()) {
            throwAlert("Error", "Introduce a valid assist/leave time");
        }
        if(typeSelected()){
            throwAlert("Error", "Introduce a type for the event");
        }
        if(isNotANumber()){
            throwAlert("Error", "Introduce a valid row/seat number");
        }

        else {
            try {
                switchAddTicket();
            } catch (DALException dalex) {
                throwAlert("Something gone wrong", dalex.getMessage());
            }
        }
    }

    private boolean isNotANumber() {
        if(!rowNumber.getText().isEmpty() || !seatNumber.getText().isEmpty()){
            try{
                Integer.parseInt(rowNumber.getText());
                Integer.parseInt(seatNumber.getText());
                return false;
            }
            catch (NumberFormatException e){
                return true;
            }
        }
        else return false;
    }

    private boolean typeSelected() {
        return ticketTypeComboBox.getSelectionModel().isEmpty();
    }

    private void switchAddTicket() throws DALException {
        switch (checkTicketCreationType()) {
            case 1 -> {
                addTicketRSAndUser();
                closeWindow();
            }
            case 2 -> {
                addTicketRS();
                closeWindow();
            }
            case 3 -> {
                addTicketGAndUser();
                closeWindow();
            }
            case 4 -> {
                addTicketG();
                closeWindow();
            }
        }
    }

    public void addTicketRS(){
        try{
            createTicketModel.addTicketRS(new TicketRS(
                            null,
                            ticketTypeComboBox.getValue(),
                            createTicketModel.getExtrasInEventAsString(),
                            assistHour.getText() + ":" + assistMin.getText(),
                            leaveHour.getText()+":"+leaveMin.getText(),
                            Integer.parseInt(rowNumber.getText()),
                            Integer.parseInt(seatNumber.getText())),
                    chosenEvent);
        }catch (DALException dal){
            throwAlert("Error creating the ticket",dal.getMessage());
        }
    }

    public void addTicketRSAndUser(){
        try{
            createTicketModel.addTicketRSAndUser(new TicketRS(
                            null,
                            ticketTypeComboBox.getValue(),
                            createTicketModel.getExtrasInEventAsString(),
                            assistHour.getText() + ":" + assistMin.getText(),
                            leaveHour.getText()+":"+leaveMin.getText(),
                            Integer.parseInt(rowNumber.getText()),
                            Integer.parseInt(seatNumber.getText())),
                    chosenEvent,
                    new User(0,
                            UserType.CUSTOMER,customerEmail.getText(),
                            null,
                            customerName.getText(),
                            customerPhone.getText()));;
        }catch (DALException dal){
            throwAlert("Error creating the ticket",dal.getMessage());
        }
    }

    public void addTicketG(){
        try{
            createTicketModel.addTicketG(new TicketG(
                    null,
                            ticketTypeComboBox.getValue(),
                            createTicketModel.getExtrasInEventAsString(),
                            assistHour.getText() + ":" + assistMin.getText(),
                            leaveHour.getText()+":"+leaveMin.getText()),
                            chosenEvent);
        }catch (DALException dal){
            throwAlert("Error creating the ticket",dal.getMessage());
        }
    }

    public void addTicketGAndUser(){
        try{
            createTicketModel.addTicketGAndUser(new TicketG(
                            null,
                            ticketTypeComboBox.getValue(),
                            createTicketModel.getExtrasInEventAsString(),
                            assistHour.getText() + ":" + assistMin.getText(),
                            leaveHour.getText()+":"+leaveMin.getText()),
                    chosenEvent,
                    new User(0,
                            UserType.CUSTOMER,customerEmail.getText(),
                            null,
                            customerName.getText(),
                            customerPhone.getText()));;
        }catch (DALException dal){
            throwAlert("Error creating the ticket",dal.getMessage());
        }
    }

    public int checkTicketCreationType() {
        if(rs) {
            if(ci){
                return 1;//ticketRS with userinfo
            }else return 2; //ticketRS without user info
        }
        if(!ci){
            return 4; //ticketG without user info
        }else return 3; //ticketG with userinfo
    }

    public void throwAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        ButtonType okButton = new ButtonType("OK");
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
    }

    @FXML
    void cancelCreatingTicket(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void openManageExtrasView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("gui/views/ticketManagement/createTicketOption/ManageExtrasView.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
                throwAlert("Something gone wrong",e.getLocalizedMessage());
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("gui/views/ticketManagement/createTicketOption/ManageTypeView.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
                throwAlert("Something gone wrong",e.getLocalizedMessage());
            }
            ManageTypeController manageTypeController = loader.getController();
            manageTypeController.setController(this);
            Stage stage = new Stage();
            stage.setTitle("Add new ticket type");
            assert root != null;
            stage.setScene(new Scene(root, 400, 150));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean timeIsCorrect() {
        return Integer.parseInt(assistHour.getText()) <= 23 && Integer.parseInt(assistHour.getText()) >= 0 &&
                Integer.parseInt(assistMin.getText()) <= 59 && Integer.parseInt(assistMin.getText()) >= 0 &&
                Integer.parseInt(leaveHour.getText()) <= 23 && Integer.parseInt(leaveHour.getText()) >= 0 &&
                Integer.parseInt(leaveMin.getText()) <= 23 && Integer.parseInt(leaveMin.getText()) >= 0;
    }

    public void setChosenEvent(Event chosenEvent) {
        this.chosenEvent = chosenEvent;
    }

    public void setExtras(ObservableList<String> observableExtras) {
        createTicketModel.setNewExtras(observableExtras);
    }

    public void populateExtrasComboBox() {
        try {
            extrasComboBox.getItems().addAll(createTicketModel.getAllExtras(chosenEvent));
        } catch (DALException dalException) {
            throwAlert("Something gone wrong", dalException.getMessage());
        }
    }

    public void repopulateExtrasComboBox() {
        extrasComboBox.getItems().clear();
        extrasComboBox.getItems().addAll(createTicketModel.getObservableExtras());
    }

    public void repopulateExtrasList() {
        extrasList.getItems().clear();
        extrasList.getItems().addAll(createTicketModel.getExtrasInEvent());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void initializeView() {
        populateExtrasComboBox();
        populateTypes();
        disableRS();
        disableUserInfo();
    }

    private void closeWindow() {
        Stage st = (Stage) cancelButton.getScene().getWindow();
        st.close();
    }

    public void populateTypes() {
        try {
            ticketTypeComboBox.getItems().addAll(createTicketModel.getAllTypesForEvent(chosenEvent));
        } catch (DALException dal) {
            throwAlert("Something gone wrong",dal.getMessage());
        }
    }

    public void addType(String text) {
        createTicketModel.addTypeToList(text);
    }

    public void repopulateTypesComboBox() {
        ticketTypeComboBox.getItems().clear();
        ticketTypeComboBox.getItems().addAll(createTicketModel.getAllObservableTypes());
    }

    public void addRowAndSeat(MouseEvent actionEvent) {
        if(rs){
            disableRS();
        }else enableRS();
    }

    public void disableRS(){//Bugged
        rs = false;
        rowNumber.setDisable(true);
        rowNumber.clear();
        seatNumber.setDisable(true);
        seatNumber.clear();
    }

    public void enableRS(){
        rs = true;
        rowNumber.setDisable(false);
        seatNumber.setDisable(false);
    }

    public void addCustomerInfo(MouseEvent actionEvent) {
        if(ci){
            disableUserInfo();
        }else enableUserInfo();
    }

    private void enableUserInfo(){
        ci = true;
        customerName.setDisable(false);
        customerEmail.setDisable(false);
        customerPhone.setDisable(false);
        customerName.clear();
        customerEmail.clear();
        customerPhone.clear();
    }

    private void disableUserInfo() {
        ci = false;
        customerName.setDisable(true);
        customerEmail.setDisable(true);
        customerPhone.setDisable(true);
        customerName.clear();
        customerEmail.clear();
        customerPhone.clear();
    }
}

