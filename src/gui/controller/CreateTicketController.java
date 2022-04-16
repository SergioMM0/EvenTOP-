package gui.controller;

import be.Event;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateTicketController implements Initializable {

    @FXML
    private CheckBox addCustomerInfoCheckBox;

    @FXML
    private JFXButton addExtraButton;

    @FXML
    private JFXButton addTicketButton;

    @FXML
    private TextField assistHour;

    @FXML
    private TextField assistMin;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private TextField customerEmail;

    @FXML
    private TextField customerName;

    @FXML
    private TextField customerPhone;

    @FXML
    private JFXButton deleteExtraButton;

    @FXML
    private JFXComboBox<String> extrasComboBox;

    @FXML
    private ListView<String> extrasList;

    @FXML
    private TextField leaveHour;

    @FXML
    private TextField leaveMin;

    @FXML
    private JFXButton manageExtrasButton;

    @FXML
    private JFXButton manageTicketTypesButton;

    @FXML
    private TextField rowNumber;

    @FXML
    private CheckBox rowsSeatCheckBox;

    @FXML
    private TextField seatNumber;

    @FXML
    private JFXComboBox<String> ticketTypeComboBox;

    private Event chosenEvent;

    @FXML
    void addExtra(ActionEvent event) {

    }

    @FXML
    void addTicket(ActionEvent event) {

    }

    @FXML
    void cancelCreatingTicket(ActionEvent event) {

    }

    @FXML
    void deleteExtra(ActionEvent event) {

    }

    @FXML
    void openManageExtrasView(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("gui/view/ManageExtrasView.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Extras of the ticket");
            assert root != null;
            stage.setScene(new Scene(root, 400, 300));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openManageTicketTypesView(ActionEvent event) {

    }

    @FXML
    void ticketTypeComboBox(ActionEvent event) {

    }

    private boolean timeIsCorrect(){
        if(Integer.parseInt(assistHour.getText())>23 || Integer.parseInt(assistHour.getText()) < 0){
            return false;
        }
        else if(Integer.parseInt(assistMin.getText())>59 || Integer.parseInt(assistMin.getText()) < 0 ){
            return false;
        }
        else return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setChosenEvent(Event chosenEvent) {
        this.chosenEvent = chosenEvent;
    }
}

