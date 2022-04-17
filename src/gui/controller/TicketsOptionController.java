package gui.controller;

import be.Event;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TicketsOptionController implements Initializable {

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXButton createTicketButton;

    @FXML
    private JFXButton manageTicketsButton;

    @FXML
    private JFXButton printTicketButton;

    private Event chosenEvent;

    @FXML
    void back(ActionEvent event) {

    }

    @FXML
    void openCreateTicketView(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("gui/view/CreateTicketView.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            CreateTicketController createTicketController = loader.getController();
            createTicketController.setChosenEvent(chosenEvent);
            createTicketController.populateExtrasComboBox();
            Stage stage = new Stage();
            stage.setTitle("Create ticket for event");
            assert root != null;
            stage.setScene(new Scene(root, 800, 500));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openManageTicketsView(ActionEvent event) {

    }

    @FXML
    void openPrintTicketView(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setChosenEvent(Event selectedItem) {
        this.chosenEvent = selectedItem;
    }
}