package gui.controller;

import be.Event;
import com.jfoenix.controls.JFXButton;
import dal.exceptions.DALException;
import gui.model.ManageExtrasModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageExtrasController implements Initializable {

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private TextField extrasField;

    @FXML
    private ListView<String> extrasListView;

    @FXML
    private JFXButton saveButton;

    private ManageExtrasModel manageExtrasModel;
    private CreateTicketController createTicketController;

    @FXML
    void addExtra(ActionEvent event) {
        if(!extrasField.getText().isBlank()){
            manageExtrasModel.addExtra(extrasField.getText());
            extrasField.clear();
        }
        repopulateExtrasList();
    }

    public void repopulateExtrasList(){
        extrasListView.getItems().clear();
        extrasListView.getItems().addAll(manageExtrasModel.getObservableExtras());
    }

    @FXML
    void cancelManageExtras(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void deleteExtra(ActionEvent event) {
        manageExtrasModel.removeExtra(extrasListView.getSelectionModel().getSelectedItem());
        repopulateExtrasList();
    }

    @FXML
    void saveExtras(ActionEvent event) {
        createTicketController.setExtras(manageExtrasModel.getObservableExtras());
        createTicketController.repopulateExtrasComboBox();
        closeWindow();
    }

    private void closeWindow(){
        Stage st = (Stage) saveButton.getScene().getWindow();
        st.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        manageExtrasModel = new ManageExtrasModel();
    }

    public void setController(CreateTicketController createTicketController) {
        this.createTicketController = createTicketController;
    }
}

