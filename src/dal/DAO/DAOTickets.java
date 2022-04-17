package dal.DAO;

import be.Event;
import be.TicketG;
import be.TicketRS;
import dal.connectionProvider.ConnectionProvider;
import dal.exceptions.DALException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
            st.addBatch();
            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setString(1, ticketG.getBarCode());
            st2.setString(2,ticketG.getTypeName());
            st2.setString(3, ticketG.getExtras());
            st2.setString(4, ticketG.getStartTime());
            st2.setString(5, ticketG.getEndTime());
            st2.addBatch();

            st.executeBatch();
            st2.executeBatch();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            throw new DALException("Not able to create Ticket for event, check again your connection", sqlException);
        }
    }

    public void addTicketRS(TicketRS ticketRS) throws DALException{
        String sql = "INSERT INTO Tickets ([BARCODE]) VALUES (?);";
        String sql2 = "INSERT INTO TicketsRS([BARCODE],[TYPE],[EXTRAS],[STARTTIME],[ENDTIME],[ROW],[SEAT]) VALUES(?,?,?,?,?,?,?);";
        try{
            Connection connection = connectionProvider.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,ticketRS.getBarCode());
            st.addBatch();
            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setString(1, ticketRS.getBarCode());
            st2.setString(2,ticketRS.getTypeName());
            st2.setString(3, ticketRS.getExtras());
            st2.setString(4, ticketRS.getStartTime());
            st2.setString(5, ticketRS.getEndTime());
            st2.setInt(6,ticketRS.getRow());
            st2.setInt(7,ticketRS.getSeat());
            st2.addBatch();

            st.executeBatch();
            st2.executeBatch();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            throw new DALException("Not able to create Ticket for event, check again your connection", sqlException);
        }
    }

    public ArrayList<String> getAllExtrasForEvent(Event event) throws DALException{
        ArrayList<String> allExtras = new ArrayList<>();
        String sql = "SELECT TicketsRS.EXTRAS \n" +
                "FROM TicketsRS\n" +
                "INNER JOIN TicketsInEvent AS TE\n" +
                "ON TE.EVENTID = (\n" +
                "    SELECT [ID] AS EID FROM Events WHERE Events.ID = ?\n" +
                ")\n" +
                "UNION\n" +
                "SELECT TicketsG.EXTRAS\n" +
                "FROM TicketsG\n" +
                "INNER JOIN TicketsInEvent AS TE\n" +
                "ON TE.EVENTID = (\n" +
                "    SELECT [ID] AS EID FROM Events WHERE Events.ID = ?\n" +
                ")";
        try{
            Connection connection = connectionProvider.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1,event.getId());
            st.setInt(2,event.getId());
            st.execute();
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                allExtras.add(rs.getString("EXTRAS"));
            }

        }catch (SQLException sqlException){
            throw new DALException("Not able to get extras for the event", sqlException);
        }
        return allExtras;
    }

}
