package gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    private JFXComboBox<?> extrasComboBox;

    @FXML
    private ListView<?> extrasList;

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
    private JFXComboBox<?> ticketTypeComboBox;

    @FXML
    void addExtra(ActionEvent event) {

    }

    @FXML
    void addTicket(ActionEvent event) {

    }

    @FXML
    void cancelCreatingTicket(ActionEvent event) {

    }

    @FXML
    void deleteExtra(ActionEvent event) {

    }

    @FXML
    void openManageExtrasView(ActionEvent event) {

    }

    @FXML
    void openManageTicketTypesView(ActionEvent event) {

    }

    @FXML
    void ticketTypeComboBox(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

