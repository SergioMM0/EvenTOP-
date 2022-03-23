package gui.controller;

import be.User;
import bll.exceptions.LoginEX;
import com.jfoenix.controls.JFXButton;
import gui.model.LoginModel;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private LoginModel loginModel;

    @FXML
    private TextField emailField;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView frontImage;

    @FXML
    private JFXButton loginButton;

    @FXML
    private PasswordField passwordField;

    public LoginController() {
        loginModel = new LoginModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchorPane.getStylesheets().add("gui/css/Login.css");
    }

    @FXML
    void login(ActionEvent event) {
        try{
            User user = loginModel.checkCredentials(emailField.getText(), passwordField.getText());
            switch (user.getType()){
                case EVENTMANAGER:
                    openEMView();
                    break;
                case ADMIN:
                    //implement
                    break;

            }
        } catch(LoginEX loginEX){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Login credentials");
                alert.setHeaderText(loginEX.getExceptionMessage());
                ButtonType okButton = new ButtonType("OK");
                alert.getButtonTypes().setAll(okButton);
                alert.showAndWait();
        }
    }

    private void openEMView(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("gui/view/EMView.fxml"));
        Parent root = null;
        try{root = loader.load();}
        catch (IOException conn){}
        assert root != null;
        EMVController emvController = new EMVController();
        root.getStylesheets().add("");  //CSS after
        loader.setController(emvController.getInstance());
        Stage stage = new Stage();
        stage.setTitle("EVENTOP");
        stage.setScene(new Scene(root,1000,650));
        stage.setResizable(false);
        stage.show();
        closeWindow();
    }

    private void closeWindow(){
        Stage st = (Stage) frontImage.getScene().getWindow();
        st.close();
    }

}