package dal.DAO;

import be.TicketG;
import dal.connectionProvider.ConnectionProvider;
import dal.exceptions.DALException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOTickets {

    ConnectionProvider connectionProvider;

    public DAOTickets(){
        connectionProvider = new ConnectionProvider();
    }

    public void addTicketG(TicketG ticketG) throws DALException{
        String sql = "INSERT INTO Tickets ([BARCODE]) VALUES (?);";
        String sql2 = "INSERT INTO TicketsG([BARCODE],[TYPE],[EXTRAS],[STARTTIME],[ENDTIME]) VALUES(?,?,?,?,?);";
        try{
            Connection connection = connectionProvider.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,ticketG.getBarCode());
            st.execute();
            PreparedStatement st2 = connection.prepareStatement(sql2);
            st.setString(1, ticketG.getBarCode());
            st.setString(2,ticketG.getTypeName());
            st.setString(3, ticketG.getExtras());
            st.setString(4, ticketG.getStartTime());
            st.setString(5, ticketG.getEndTime());
            st.execute();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            throw new DALException("Not able to connect to database", sqlException);
        }
    }

}
