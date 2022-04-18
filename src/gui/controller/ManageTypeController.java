package gui.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageTypeController implements Initializable {

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton saveButton;

    @FXML
    private TextField typeField;

    private CreateTicketController createTicketController;

    @FXML
    void closeView(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void saveType(ActionEvent event) {
        if(!typeField.getText().isBlank()){
            createTicketController.addType(typeField.getText());
            createTicketController.repopulateTypesComboBox();
            closeWindow();
        }
    }

    private void closeWindow(){
        Stage st = (Stage) cancelButton.getScene().getWindow();
        st.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void setController(CreateTicketController createTicketController) {
        this.createTicketController = createTicketController;
    }
}
