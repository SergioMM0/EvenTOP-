package gui.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainLogController implements Initializable {

    private boolean isEM = false;

    @FXML
    private TextField emailField;

    @FXML
    private ImageView frontImage;

    @FXML
    private PasswordField passwordField;

    @FXML
    private JFXButton switchLoginButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void login(ActionEvent event) {

    }

    @FXML
    void switchLoginAct(ActionEvent event) {
        isEM = !isEM;
        if (isEM){
            switchLoginButton.setText("I'm a customer");
        }
        else switchLoginButton.setText("I'm an Event Manager");
    }
}