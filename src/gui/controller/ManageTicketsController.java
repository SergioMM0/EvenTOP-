package gui.controller;

import be.Event;
import be.Ticket;
import com.jfoenix.controls.JFXButton;
import dal.exceptions.DALException;
import gui.model.ManageTicketsModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageTicketsController {

    @FXML
    private TableColumn<String, Ticket> assistTimeCol;

    @FXML
    private JFXButton backButton;

    @FXML
    private TableColumn<String, Ticket> barcodeCol;

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
    private TableColumn<String, Ticket> extrasCol;

    @FXML
    private TableColumn<String, Ticket> leaveTimeCol;

    @FXML
    private TableColumn<Integer, Ticket> rowCol;

    @FXML
    private JFXButton searchButton;

    @FXML
    private TableColumn<Integer, Ticket> seatCol;

    @FXML
    private TableView<Ticket> ticketTableView;

    @FXML
    private TableColumn<String, Ticket> ticketTypeCol;

    private Event chosenEvent;
    private ManageTicketsModel manageTicketsModel;
    private static final String errTitle = "Something went wrong";

    public ManageTicketsController(){
        manageTicketsModel = new ManageTicketsModel();
    }

    @FXML
    void changeAssistLeaveTime(ActionEvent event) {

    }

    @FXML
    void changeExtrasToTicket(ActionEvent event) {

    }

    @FXML
    void changeSeatRowToTicket(ActionEvent event) {

    }

    @FXML
    void changeTypeToTicket(ActionEvent event) {

    }

    @FXML
    void deleteTicket(ActionEvent event) {

    }

    @FXML
    void goBackToTicketOptions(ActionEvent event) {

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
}
