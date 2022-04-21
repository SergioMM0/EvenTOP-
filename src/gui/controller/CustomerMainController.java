package gui.controller;

import be.Ticket;
import com.jfoenix.controls.JFXButton;
import gui.model.CustomerMainModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class CustomerMainController {

    @FXML
    private JFXButton contactAnEmButton;

    @FXML
    private Label dateLBL;

    @FXML
    private PasswordField emailNameField;

    @FXML
    private Label endTimeLBL;

    @FXML
    private Label eventNameLBL;

    @FXML
    private Label extrasLBL;

    @FXML
    private Label locationLBL;

    @FXML
    private JFXButton logOutButton;

    @FXML
    private PasswordField phoneNumberField;

    @FXML
    private JFXButton printTicketButton;

    @FXML
    private Label rowLBL;

    @FXML
    private Label seatLBL;

    @FXML
    private Label startTimeLBL;

    @FXML
    private JFXButton updateUserInfoButton;

    @FXML
    private PasswordField userNameField;

    private CustomerMainModel model;

    public CustomerMainController(){
        model = new CustomerMainModel();
    }

    @FXML
    void contactAnEm(ActionEvent event) {

    }

    @FXML
    void logOut(ActionEvent event) {

    }

    @FXML
    void printTicket(ActionEvent event) {

    }

    @FXML
    void updateUserInfo(ActionEvent event) {

    }

    public void initializeView(){

    }

    public void setUpTicketLBLs(){

    }
}

