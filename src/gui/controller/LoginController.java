package gui.controller;

import be.User;
import bll.exceptions.BLLException;
import com.jfoenix.controls.JFXButton;
import dal.exceptions.DALException;
import gui.model.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private Label credentialsLabel;

    @FXML
    private JFXButton switchUserButton;

    @FXML
    private PasswordField passwordField;

    private static final String errTitle = "Something went wrong";
    private int ints = 0;
    private boolean isCustomer;
    private User logedUser;

    public LoginController() {
        loginModel = new LoginModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isCustomer = false;
        anchorPane.getStylesheets().add("gui/css/Login.css");
    }

    @FXML
    void login(ActionEvent event) {
        if(!isCustomer){
            try{
                logedUser = loginModel.checkCredentials(emailField.getText(), passwordField.getText());
                System.out.println(logedUser);
                switch (logedUser.getType()){
                    case EVENTMANAGER:
                        openEMView();
                        break;
                    case ADMIN:
                        //implement
                        break;
                }
            } catch(BLLException loginEX){
                if(ints < 2){
                    throwAlert("User not found, try again");
                    ints++;
                }else{
                    throwAlert("If you're a customer, make sure you have checked the box.\n" +
                            "your user and password are the last 8 digits of the barcode in your ticket");
                    ints--;
                }
            } catch (DALException databaseEx) {
                throwAlert("Not able to connect to database");
            }
        }
        else{
            if(!barcodeMatches()){
                throwAlert("Your ticket was not found, try again");
            }else {
                try {
                    logedUser = loginModel.checkBarcode(emailField.getText());
                    openCustomerView();
                } catch (BLLException | DALException ex) {
                    throwAlert(ex.getMessage());
                }
            }
        }
    }

    private void openCustomerView() {
        
    }

    private boolean barcodeMatches() {
        return emailField.getText().equals(passwordField.getText());
    }

    @FXML
    void switchLoginForm(ActionEvent event) {
        if(!isCustomer){
            isCustomer = true;
            credentialsLabel.setText("Introduce the last 8 digits of your barcode");
            switchUserButton.setText("I'm a User!");
        }else{
            isCustomer = false;
            credentialsLabel.setText("Introduce your user credentials");
            switchUserButton.setText("I'm a Customer!");
        }
    }

    private void openEMView(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("gui/views/userViews/EMView.fxml"));
        Parent root = null;
        try{root = loader.load();}
        catch (IOException ignored){
            ignored.printStackTrace();
        }
        EMVController emvController = loader.getController();
        emvController.setUser(logedUser);
        assert root != null;
        root.getStylesheets().add("");  //CSS after
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

    public void throwAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(errTitle);
        alert.setHeaderText(message);
        ButtonType okButton = new ButtonType("OK");
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
    }
}