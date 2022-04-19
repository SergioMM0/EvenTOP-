package gui.controller;

import be.Ticket;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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

}
