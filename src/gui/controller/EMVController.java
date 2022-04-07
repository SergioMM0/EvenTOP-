package gui.controller;

import be.Event;
import be.User;
import com.jfoenix.controls.JFXButton;
import dal.exceptions.DALException;
import gui.model.EMVModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class EMVController implements Initializable {

    @FXML
    private JFXButton assistanceListButton;

    @FXML
    private TableColumn<Integer, Event> assistantsColumn;

    @FXML
    private AnchorPane bottomAnchorPane;

    @FXML
    private JFXButton createEventButton;

    @FXML
    private TableColumn<Date,Event> dateColumn;

    @FXML
    private TableColumn<String, Event> emsColumn;

    @FXML
    private JFXButton eventInfoButton;

    @FXML
    private Label eventManagerLabel;

    @FXML
    private TableView<Event> eventTableView;

    @FXML
    private Label eventsCounterLabel;

    @FXML
    private JFXButton infoButton;

    @FXML
    private TableColumn<String,Event> locationColumn;

    @FXML
    private JFXButton logOutButton;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private JFXButton manageTicketsButton;

    @FXML
    private TableColumn<String,Event> nameColumn;

    @FXML
    private AnchorPane subBottomAnchorPane;

    @FXML
    private AnchorPane topAnchorPane;

    private EMVModel emvModel;

    public EMVController(){
        emvModel = new EMVModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateEventsTable();
    }

    @FXML
    void openNewEventView(ActionEvent event) {
        openNewEventWindow();
    }

    private void openNewEventWindow(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/view/NewEventView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("EvenTOP");
            stage.setScene(new Scene(root, 600, 420));
            stage.setResizable(false);
            //root.getStylesheets().add("");  //CSS after
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openEventInfo(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/view/EventInfo.fxml"));
            Stage stage = new Stage();
            stage.setTitle("EvenTOP");
            stage.setScene(new Scene(root, 600, 420));
            stage.setResizable(false);
            //root.getStylesheets().add("");  //CSS after
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openAssistanceList(ActionEvent event) {

    }

    @FXML
    void openManageTickets(ActionEvent event) {

    }

    @FXML
    void logOut(ActionEvent event) {

    }

    @FXML
    void openEmInfo(ActionEvent event) {

    }



    public void populateEventsTable(){
        try{
            eventTableView.getItems().addAll(emvModel.getAllEvents());
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            emsColumn.setCellValueFactory(new PropertyValueFactory<>("eventManagers"));

        }catch (DALException dalException){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("An error has ocurred");
            alert.setHeaderText("Error updating events table, please check your connection");
            ButtonType okButton = new ButtonType("OK");
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
        }

    }
}

