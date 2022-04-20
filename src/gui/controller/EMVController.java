package gui.controller;

import be.Event;
import com.jfoenix.controls.JFXButton;
import dal.exceptions.DALException;
import gui.model.EMVModel;
import gui.model.EventInfoModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    private Event chosenEvent;
    private EventInfoModel eventInfoModel;
    private static final String errTitle = "Something went wrong";

    public EMVController(){
        emvModel = new EMVModel();
        eventInfoModel = new EventInfoModel();
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
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("gui/views/eventManagement/NewEventView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NewEventController newEventController = loader.getController();
        newEventController.setController(this); //establishes the main controller as the controller.
        Stage stage = new Stage();
        stage.setTitle("Add event");
        stage.setScene(new Scene(root, 600, 420));
        stage.show();
    }

    @FXML
    void openEventInfo(ActionEvent event) {
        if(chosenEvent == null){
            throwAlert(errTitle,"Please select an event");
        }
        else{
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("gui/views/eventManagement/EventInfo.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                EventInfoController eventInfoController = loader.getController();
                eventInfoController.setController(this); //establishes the EMVController as the controller.
                eventInfoController.populateEventInfo(eventTableView.getSelectionModel().getSelectedItem());
                Stage stage = new Stage();
                stage.setTitle("Event's info");
                assert root != null;
                stage.setScene(new Scene(root, 600, 450));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    void openAssistanceList(ActionEvent event) {

    }

    @FXML
    void openManageTickets(ActionEvent event) {
        if(chosenEvent == null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("EvenTOP");
            alert.setHeaderText("Chose an event to continue");
            ButtonType okButton = new ButtonType("OK");
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
        }
        else{
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("gui/views/ticketManagement/TicketsOptionView.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                TicketsOptionController ticketsOptionController = loader.getController();
                ticketsOptionController.setChosenEvent(eventTableView.getSelectionModel().getSelectedItem());
                Stage stage = new Stage();
                stage.setTitle("Ticket options");
                assert root != null;
                stage.setScene(new Scene(root, 400, 300));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
            assistantsColumn.setCellValueFactory(new PropertyValueFactory<>("tickets"));
            locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        }catch (DALException dalException){
            throwAlert(errTitle,dalException.getMessage());
        }
    }

    public void repopulateEventsTable(){
        try{
            eventTableView.refresh();
            eventTableView.getItems().clear();
            eventTableView.getItems().addAll(emvModel.getAllEvents());
        }catch(DALException dalException){
            throwAlert(errTitle,dalException.getMessage());
        }

    }

    public void throwAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        ButtonType okButton = new ButtonType("OK");
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
    }

    @FXML
    void eventClicked(MouseEvent event) {
        chosenEvent = eventTableView.getSelectionModel().getSelectedItem();
    }
}

