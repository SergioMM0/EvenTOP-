package dal.DAO;

import be.Event;
import be.TicketG;
import be.TicketRS;
import dal.connectionProvider.ConnectionProvider;
import dal.exceptions.DALException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOTickets {

    ConnectionProvider connectionProvider;

    public DAOTickets(){
        connectionProvider = new ConnectionProvider();
    }

    public void addTicketG(TicketG ticketG,Event event) throws DALException{
        String sql = "INSERT INTO Tickets ([BARCODE]) VALUES (?);";
        String sql2 = "INSERT INTO TicketsG([BARCODE],[TYPE],[EXTRAS],[STARTTIME],[ENDTIME],[EVENTID]) VALUES(?,?,?,?,?,?);";
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
            st2.setInt(6,event.getId());
            st2.addBatch();

            st.executeBatch();
            st2.executeBatch();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            throw new DALException("Not able to create Ticket for event, check again your connection", sqlException);
        }
    }

    public void addTicketRS(TicketRS ticketRS,Event event) throws DALException{
        String sql = "INSERT INTO Tickets ([BARCODE]) VALUES (?);";
        String sql2 = "INSERT INTO TicketsRS([BARCODE],[TYPE],[EXTRAS],[STARTTIME],[ENDTIME],[ROW],[SEAT],[EVENTID]) VALUES(?,?,?,?,?,?,?,?);";
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
            st2.setInt(8,event.getId());
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
        String sql = "SELECT [EXTRAS]\n" +
                "FROM TicketsG\n" +
                "WHERE EVENTID = ? \n" +
                "UNION\n" +
                "SELECT [EXTRAS]\n" +
                "FROM TicketsRS\n" +
                "WHERE EVENTID = ?";
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
            throw new DALException("Not able to get extras for the event, check your connection", sqlException);
        }
        return allExtras;
    }

    public ArrayList<String> getAllTypesForEvent(Event event) throws DALException{
        ArrayList<String> allTypes = new ArrayList<>();
        String sql = "SELECT [TYPE]\n" +
                "FROM TicketsG\n" +
                "WHERE EVENTID = ? \n" +
                "UNION \n" +
                "SELECT [TYPE]\n" +
                "FROM TicketsRS\n" +
                "WHERE EVENTID = ?";
        try{
            Connection connection = connectionProvider.getConnection();
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1,event.getId());
            st.setInt(2,event.getId());
            st.execute();
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                allTypes.add(rs.getString("TYPE"));
            }

        }catch(SQLException sqlex){
            throw new DALException("Not able to get types for event, check your connection", sqlex);
        }
        return allTypes;
    }

    public boolean checkBarcode(String string) throws DALException{
        String sql = "SELECT * \n" +
                "FROM Tickets\n" +
                "WHERE BARCODE = ?";
        try{
            Connection connection = connectionProvider.getConnection();
            PreparedStatement st = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            st.setString(1,string);
            st.execute();
            ResultSet rs = st.getResultSet();
            return rs.next(); //true = taken & false = not taken
        }catch (SQLException ex){
            throw new DALException("Not able to check if the barcode exists, check your connection", ex);
        }
    }

    public List<TicketG> getAllTickets() throws DALException{
        List<TicketG> allTickets = new ArrayList<>();
        String sql = "SELECT [BARCODE],[TYPE],[EXTRAS],[STARTTIME],[ENDTIME]\n" +
                "FROM TicketsG\n" +
                "UNION\n" +
                "SELECT [BARCODE],[TYPE],[EXTRAS],[STARTTIME],[ENDTIME]\n" +
                "FROM TicketsRS";
        try{
            Connection connection = connectionProvider.getConnection();
            PreparedStatement st = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            st.execute();
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                allTickets.add(new TicketG(rs.getString("BARCODE"),
                        rs.getString("TYPE"),
                        rs.getString("EXTRAS"),
                        rs.getString("STARTTIME"),
                        rs.getString("ENDTIME")
                        ));

            }
        }catch (SQLException sqlException){
            throw new DALException("Not able to get all tickets from database, try again",sqlException);
        }
        return allTickets;
    }

    public void updateTicketType(TicketG ticketG)throws DALException{
        String sql = "IF EXISTS (\n" +
                "    SELECT [BARCODE]\n" +
                "    FROM TicketsRS\n" +
                "    WHERE BARCODE = ?)\n" +
                "BEGIN \n" +
                "    UPDATE TicketsRS SET [TYPE] = ? WHERE BARCODE = ?\n" +
                "END\n" +
                "ELSE\n" +
                "BEGIN \n" +
                "UPDATE TicketsG SET [TYPE] = ? WHERE BARCODE = ?\n" +
                "END";
        try{
            Connection connection = connectionProvider.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,ticketG.getBarCode());
            st.setString(2,ticketG.getTypeName());
            st.setString(3,ticketG.getBarCode());
            st.setString(4,ticketG.getTypeName());
            st.setString(5,ticketG.getBarCode());
            st.execute();
        }catch (SQLException sqlException){
            throw new DALException("Not able to update the type of the ticket", sqlException);
        }
    }

    public void updateExtrasFromTicket(TicketG ticketg) throws DALException{
        String sql = "IF EXISTS (\n" +
                "    SELECT [BARCODE]\n" +
                "    FROM TicketsRS\n" +
                "    WHERE BARCODE = ?)\n" +
                "BEGIN \n" +
                "    UPDATE TicketsRS SET [EXTRAS] = ? WHERE BARCODE = ?\n" +
                "END\n" +
                "ELSE\n" +
                "BEGIN \n" +
                "UPDATE TicketsG SET [Extras] = ? WHERE BARCODE = ?\n" +
                "END";
        try{
            Connection connection = connectionProvider.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,ticketg.getBarCode());
            st.setString(2,ticketg.getTypeName());
            st.setString(3,ticketg.getBarCode());
            st.setString(4,ticketg.getTypeName());
            st.setString(5,ticketg.getBarCode());
            st.execute();
        }catch(SQLException sqlException){
            throw new DALException("Not able to update extras of the ticket",sqlException);
        }
    }

    public void updateAssistLeaveTime(TicketG ticketG)throws DALException{
        String sql = "IF EXISTS (\n" +
                "    SELECT [BARCODE]\n" +
                "    FROM TicketsRS\n" +
                "    WHERE BARCODE = ?)\n" +
                "BEGIN \n" +
                "    UPDATE TicketsRS SET [STARTTIME] = ? ,[ENDTIME] = ? WHERE BARCODE = ?\n" +
                "END\n" +
                "ELSE\n" +
                "BEGIN \n" +
                "UPDATE TicketsG SET [STARTTIME] = ? ,[ENDTIME] = ? WHERE BARCODE = ?\n" +
                "END";
        try{
            Connection connection = connectionProvider.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,ticketG.getBarCode());
            st.setString(2,ticketG.getStartTime());
            st.setString(3,ticketG.getEndTime());
            st.setString(4,ticketG.getBarCode());
            st.setString(5,ticketG.getStartTime());
            st.setString(6,ticketG.getEndTime());
            st.setString(7,ticketG.getBarCode());
            st.execute();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            throw new DALException("Not able to update assist/leave time for the ticket",sqlException);
        }
    }
}
