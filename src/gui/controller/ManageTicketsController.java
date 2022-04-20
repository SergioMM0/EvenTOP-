package gui.controller;

import be.Event;
import be.Ticket;
import be.TicketG;
import com.jfoenix.controls.JFXButton;
import dal.exceptions.DALException;
import gui.model.ManageTicketsModel;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

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
    private TextField searchField;

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
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("gui/views/ticketManagement/ManageTickets/ChangeAssistLeaveTimeView.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ManageChangeAssistLeaveController controller = loader.getController();
            controller.setController(this);
            controller.setTicket(chosenTicket);
            Stage stage = new Stage();
            stage.setTitle("Event's info");
            assert root != null;
            stage.setScene(new Scene(root, 300, 170));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void changeExtrasToTicket(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("gui/views/ticketManagement/ManageTickets/ChangeExtras.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ManageChangeExtrasController controller = loader.getController();
            controller.setController(this);
            controller.setChosenEvent(chosenEvent);
            controller.setChosenTicket(chosenTicket);
            controller.populateAllExtras();
            controller.populateExtrasOnEvent();
            Stage stage = new Stage();
            stage.setTitle("Event's info");
            assert root != null;
            stage.setScene(new Scene(root, 475, 330));
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
            controller.setController(this);
            controller.setChosenEvent(chosenEvent);
            controller.setChosenTicket(chosenTicket);
            controller.populateComboBox(chosenEvent);
            Stage stage = new Stage();
            stage.setTitle("Event's info");
            assert root != null;
            stage.setScene(new Scene(root, 450, 180));
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

    public void initializeView() {
        populateTicketsView();
        initializeTicketFilter();
    }

    public void populateTicketsView() {
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

    public void repopulateTicketsView(){
            ticketTableView.getItems().clear();
            populateTicketsView();
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

    public void initializeTicketFilter(){
        FilteredList<TicketG> filteredTickets = new FilteredList<>(manageTicketsModel.getObservableTickets(), b -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredTickets.setPredicate(ticketG ->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(ticketG.getBarCode().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else return ticketG.getTypeName().toLowerCase().contains(lowerCaseFilter);
            });
        });
        SortedList<TicketG> sortedTickets = new SortedList<>(filteredTickets);
        sortedTickets.comparatorProperty().bind(ticketTableView.comparatorProperty());
        ticketTableView.setItems(sortedTickets);
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
