package gui.controller;

import com.jfoenix.controls.JFXButton;
import gui.model.ManageChangeExtrasModel;
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
    private ListView<?> extrasAvailableListView;

    @FXML
    private ListView<?> extrasOnTicketListView;

    @FXML
    private TextField newExtraField;

    @FXML
    private JFXButton removeButton;

    @FXML
    private JFXButton saveButton;
    private ManageTicketsController manageTicketsController;
    private ManageChangeExtrasModel manageChangeExtrasModel;

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

    }
}