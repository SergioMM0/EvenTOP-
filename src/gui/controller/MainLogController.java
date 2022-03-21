package gui.controller;

import bll.exceptions.LoginEX;
import gui.model.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainLogController implements Initializable {

    private LoginModel loginModel;

    @FXML
    private TextField emailField;

    @FXML
    private ImageView frontImage;

    @FXML
    private PasswordField passwordField;

    public MainLogController() {
        loginModel = new LoginModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void displayException(){

    }

    @FXML
    void login(ActionEvent event) {
        try{
            loginModel.checkCredentials(emailField.getText(), passwordField.getText());
        } catch(LoginEX loginEX){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Login credentials");
                alert.setHeaderText(loginEX.getExceptionMessage());
                ButtonType okButton = new ButtonType("OK");
                alert.getButtonTypes().setAll(okButton);
                alert.showAndWait();
        }
    }

}