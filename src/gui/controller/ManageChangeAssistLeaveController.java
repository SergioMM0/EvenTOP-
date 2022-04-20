package gui.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageChangeAssistLeaveController implements Initializable {

    @FXML
    private TextField assistHour;

    @FXML
    private TextField assistMin;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private TextField leaveHour;

    @FXML
    private TextField leaveMin;

    @FXML
    private JFXButton saveButton;

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void saveChanges(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}

