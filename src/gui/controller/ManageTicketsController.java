package gui.controller;

import be.Event;
import be.Ticket;
import be.TicketG;
import com.jfoenix.controls.JFXButton;
import dal.exceptions.DALException;
import gui.model.ManageTicketsModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageTicketsController {

    @FXML
    private TableColumn<String, TicketG> assistTimeCol;

    @FXML
    private JFXButton backButton;

    @FXML
    private TableColumn<String, TicketG> barcodeCol;

    @FXML
    private JFXButton changeAssistLeaveButton;

    @FXML
    private JFXButton changeExtrasButton;

    @FXML
    private JFXButton changeSeatRowButton;

    @FXML
    private JFXButton changeTypeButton;

    @FXML
    private JFXButton deleteTicketButton;

    @FXML
    private TableColumn<String, TicketG> extrasCol;

    @FXML
    private TableColumn<String, TicketG> leaveTimeCol;

    @FXML
    private TableColumn<Integer, TicketG> rowCol;

    @FXML
    private JFXButton searchButton;

    @FXML
    private TableColumn<Integer, TicketG> seatCol;

    @FXML
    private JFXButton printTicketButton;

    @FXML
    private TableView<TicketG> ticketTableView;

    @FXML
    private TableColumn<String, TicketG> ticketTypeCol;

    private Event chosenEvent;
    private ManageTicketsModel manageTicketsModel;
    private static final String errTitle = "Something went wrong";
    private TicketG chosenTicket;

    public ManageTicketsController(){
        manageTicketsModel = new ManageTicketsModel();
    }

    @FXML
    void changeAssistLeaveTime(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("gui/views/eventManagement/EventInfo.fxml"));
            Parent root = null; //tochangeview
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage stage = new Stage();
            stage.setTitle("Event's info");
            assert root != null;
            stage.setScene(new Scene(root, 600, 450));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void changeExtrasToTicket(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("gui/views/eventManagement/EventInfo.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Event's info");
            assert root != null;
            stage.setScene(new Scene(root, 600, 450));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void changeSeatRowToTicket(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("gui/views/eventManagement/EventInfo.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Event's info");
            assert root != null;
            stage.setScene(new Scene(root, 600, 450));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void changeTypeToTicket(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("gui/views/ticketManagement/ManageTickets/TicketType.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ManageChangeTypeToTicketController controller = loader.getController();
            controller.setChosenEvent(chosenEvent);
            controller.setChosenTicket(chosenTicket);
            controller.populateComboBox(chosenEvent);
            Stage stage = new Stage();
            stage.setTitle("Event's info");
            assert root != null;
            stage.setScene(new Scene(root, 600, 450));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deleteTicket(ActionEvent event) {

    }

    @FXML
    void goBackToTicketOptions(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void searchInTicketTable(ActionEvent event) {

    }

    public void initializeView() {
        populateTicketsView();
    }

    private void populateTicketsView() {
        try{
            ticketTableView.getItems().addAll(manageTicketsModel.getAllTickets());
            ticketTypeCol.setCellValueFactory(new PropertyValueFactory<>("typeName"));
            assistTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
            leaveTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
            extrasCol.setCellValueFactory(new PropertyValueFactory<>("extras"));
            barcodeCol.setCellValueFactory(new PropertyValueFactory<>("barCode"));

        }catch (DALException dalException){
            dalException.printStackTrace();
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

    public void setChosenEvent(Event event) {
        this.chosenEvent = event;
    }


    @FXML
    void printTicket(ActionEvent event) {

    }

    @FXML
    void ticketClicked(MouseEvent event) {
        this.chosenTicket = ticketTableView.getSelectionModel().getSelectedItem();
    }

    private void closeWindow() {
        Stage st = (Stage) backButton.getScene().getWindow();
        st.close();
    }
}
