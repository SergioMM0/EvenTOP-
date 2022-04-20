package gui.controller;

import be.TicketG;
import com.jfoenix.controls.JFXButton;
import dal.exceptions.DALException;
import gui.model.ManageChangeAssistLeaveModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    private ManageTicketsController mainController;
    private TicketG chosenTicket;
    private static final String errTitle = "Something went wrong";
    private ManageChangeAssistLeaveModel model;

    @FXML
    void cancel(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void saveChanges(ActionEvent event) {
        try{
            if (timeIsCorrect()) {
                try {
                    chosenTicket.setStartTime(assistHour.getText(), assistMin.getText());
                    chosenTicket.setEndTime(leaveHour.getText(), leaveMin.getText());
                    model.updateTicketTime(chosenTicket);
                    mainController.repopulateTicketsView();
                } catch (DALException dalException) {
                    throwAlert(dalException.getMessage());
                }
                closeWindow();
            } else throwAlert("Please introduce a valid assist/leave time");
        }catch(NumberFormatException | NullPointerException ex){
            throwAlert("Please introduce a valid assist/leave time");
        }

    }

    private boolean timeIsCorrect() {
        if (Integer.parseInt(assistHour.getText()) > 23 || Integer.parseInt(assistHour.getText()) < 0) {
            return false;
        }
        if (Integer.parseInt(assistMin.getText()) > 59 || Integer.parseInt(assistMin.getText()) < 0) {
            return false;
        }
        if (Integer.parseInt(leaveHour.getText()) > 23 || Integer.parseInt(leaveHour.getText()) < 0) {
            return false;
        }
        if (Integer.parseInt(leaveMin.getText()) > 59 || Integer.parseInt(leaveMin.getText()) < 0) {
            return false;
        }
        try {
            Integer.parseInt(assistHour.getText());
            Integer.parseInt(assistMin.getText());
            Integer.parseInt(leaveHour.getText());
            Integer.parseInt(leaveMin.getText());
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new ManageChangeAssistLeaveModel();
    }

    public void setTicket(TicketG ticketG) {
        this.chosenTicket = ticketG;
    }

    public void setController(ManageTicketsController manageTicketsController) {
        this.mainController = manageTicketsController;
    }

    public void throwAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(errTitle);
        alert.setHeaderText(message);
        ButtonType okButton = new ButtonType("OK");
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
    }

    private void closeWindow() {
        Stage st = (Stage) cancelButton.getScene().getWindow();
        st.close();
    }
}

