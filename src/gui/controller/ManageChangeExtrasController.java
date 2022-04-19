package gui.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private ListView<?> extrasAvailableView;

    @FXML
    private TextField newExtraField;

    @FXML
    private JFXButton removeExtraFromTicketButton;

    @FXML
    private JFXButton saveButton;

    @FXML
    private ListView<?> ticketExtrasListView;

    @FXML
    void addExtraToTicket(ActionEvent event) {

    }

    @FXML
    void addNewExtraToTicket(ActionEvent event) {

    }

    @FXML
    void backToManageTickets(ActionEvent event) {

    }

    @FXML
    void removeExtraFromTicket(ActionEvent event) {

    }

    @FXML
    void saveChanges(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
