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
        } else try {
            doAddTicket();
        } catch (DALException dalex) {
            throwAlert("Something gone wrong", dalex.getMessage());
        }

    }

    private void doAddTicket() throws DALException {
        switch (checkTicketCreationType()) {
            case 1:
            
        }
    }

    public int checkTicketCreationType() {
        if (rowsSeatCheckBox.isSelected() && addCustomerInfoCheckBox.isSelected()) {
            return 1; //ticketRS with user info
        }
        if (!rowsSeatCheckBox.isSelected() && addCustomerInfoCheckBox.isSelected()) {
            return 2; //ticketG with user info
        }
        if (rowsSeatCheckBox.isSelected() && !addCustomerInfoCheckBox.isSelected()) {
            return 3; // ticketRS without user info
        } else return 4; // ticketG without user info
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("gui/view/ManageTypeView.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
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
    }

    private void closeWindow() {
        Stage st = (Stage) cancelButton.getScene().getWindow();
        st.close();
    }

    public void populateTypes() {
        try {
            ticketTypeComboBox.getItems().addAll(createTicketModel.getAllTypesForEvent(chosenEvent));
        } catch (DALException dal) {
            dal.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Something gone wrong");
            alert.setHeaderText(dal.getMessage());
            ButtonType okButton = new ButtonType("OK");
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
        }

    }

    public void addType(String text) {
        createTicketModel.addTypeToList(text);
    }

    public void repopulateTypesComboBox() {
        ticketTypeComboBox.getItems().clear();
        ticketTypeComboBox.getItems().addAll(createTicketModel.getAllObservableTypes());
    }
}

