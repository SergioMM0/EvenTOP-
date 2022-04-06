package dal.DAO;

import be.TicketRS;
import be.TicketG;
import dal.connectionProvider.ConnectionProvider;

public class DAOTickets {

    ConnectionProvider connectionProvider;

    public DAOTickets(){
        connectionProvider = new ConnectionProvider();
    }



}
