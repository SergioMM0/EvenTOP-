package dal.DAO;

import be.Event;
import be.User;
import dal.connectionProvider.ConnectionProvider;
import dal.exceptions.DALException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOEvents {

    ConnectionProvider connectionProvider;

    public DAOEvents() {
        this.connectionProvider = new ConnectionProvider();
    }

    public void addEvent(Event event) throws DALException {
        try {
            String sql = "INSERT INTO Events([Name],[Date],[Location],[Info],[StartTime],[EndTime]) VALUES (?,?,?,?,?,?)";
            Connection connection = connectionProvider.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, event.getName());
            st.setDate(2, (Date) event.getDate());
            st.setString(3, event.getLocation());
            st.setString(4, event.getInfo());
            st.setString(5, event.getStartTime());
            st.setString(6, event.getEndTime());
            st.execute();
        } catch (SQLException sqlException) {
            throw new DALException("Problem adding the event to the database", sqlException);
        }
    }

    public void addEventAndEMs(Event event, List<User> ems) throws DALException {
        try {
            addEvent(event);
            String sql = "SELECT [ID] FROM Events WHERE [Name] = ?";
            Connection connection = connectionProvider.getConnection();
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, event.getName());
            st.execute();
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                event.setId(rs.getInt("ID"));
            }
        } catch (SQLException sqlException) {
            throw new DALException("Problem adding the event to the database", sqlException);
        }
        try {
            String sql2 = "INSERT INTO [EmsInEvent] (EventId,EmID) VALUES (?,?)";
            Connection connection2 = connectionProvider.getConnection();
            PreparedStatement st2 = connection2.prepareStatement(sql2);
            for (User user : ems) {
                st2.setInt(1, event.getId());
                st2.setInt(2, user.getId());
                st2.addBatch();
            }
            st2.executeBatch();
        } catch (SQLException sql2) {
            sql2.printStackTrace();
            throw new DALException("Not able to add the Event Managers in charge for the event", sql2);
        }
    }

    private java.util.Date parseDate(Date sqlDate) {
        return new Date(sqlDate.getTime());
    }

    public List<Event> getAllEvents() throws DALException {
        List<Event> allEvents = new ArrayList<>();
        try {
            String sql = "SELECT [ID],[Name],[Date],[Location],[Info],[StartTime],[EndTime] FROM Events ";
            Connection connection = connectionProvider.getConnection();
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.execute();
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                String ems = null;
                int tickets = 0;
                Event event = new Event(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        parseDate(rs.getDate("Date")),
                        ems,
                        rs.getString("Location"),
                        tickets,
                        rs.getString("Info"),
                        rs.getString("StartTime"),
                        rs.getString("EndTime")
                );
                try {
                    String sql2 = "SELECT [Name] FROM Users WHERE Users.ID IN (SELECT [EmID] FROM EmsInEvent WHERE EventID = ?)";
                    Connection connection1 = connectionProvider.getConnection();
                    PreparedStatement st2 = connection1.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
                    st2.setInt(1, event.getId());
                    st2.execute();
                    ResultSet rs2 = st2.getResultSet();
                    while (rs2.next()) {
                        event.addEventManager(rs2.getString("Name"));
                    }
                } catch (SQLException sqlException2) {
                    throw new DALException("Error getting the EventManagers", sqlException2);
                }
                try {
                    String sql3 = "SELECT g, rs, (g + rs) AS NumberOfTickets\n" +
                            "FROM(\n" +
                            "    SELECT (\n" +
                            "        SELECT COUNT(BARCODE)\n" +
                            "        FROM TicketsG\n" +
                            "        WHERE EVENTID = ?) AS g,\n" +
                            "        (SELECT COUNT(BARCODE)\n" +
                            "        FROM TicketsRS\n" +
                            "        WHERE EVENTID = ?) AS rs\n" +
                            ") t";
                    Connection connection2 = connectionProvider.getConnection();
                    PreparedStatement st3 = connection2.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS);
                    st3.setInt(1, event.getId());
                    st3.setInt(2,event.getId());
                    st3.execute();
                    ResultSet rs3 = st3.getResultSet();
                    while (rs3.next()) {
                        tickets = rs3.getInt("NumberOfTickets");
                        event.setTickets(tickets);
                    }
                } catch (SQLException sqlException3) {
                    throw new DALException("Error getting the number of tickets", sqlException3);
                }
                allEvents.add(event);
            }
        } catch (SQLException sqlException) {
            throw new DALException("Not able to connect to database", sqlException);
        }
        return allEvents;
    }

    public void updateEventAndEms(Event event, List<User> ems) throws DALException {
        try {
            String sql = "UPDATE Events SET [Name] = ?, [Date] = ?, [Location] = ?,[Info] = ?,[StartTime] = ?,[EndTime] = ? WHERE ID = ?";
            Connection connection = connectionProvider.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, event.getName());
            st.setDate(2, (Date) event.getDate());
            st.setString(3, event.getLocation());
            st.setString(4, event.getInfo());
            st.setString(5, event.getStartTime());
            st.setString(6, event.getEndTime());
            st.setInt(7, event.getId());
            st.execute();

        } catch (SQLException sql) {
            sql.printStackTrace();
            throw new DALException("Not able to connect to update the Event and Event Managers in charge", sql);
        }
        if(ems.isEmpty()){
            try{
                String sql = "DELETE FROM EmsInEvent WHERE EventID = ?;";
                Connection connection = connectionProvider.getConnection();
                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1,event.getId());
                st.execute();
            }catch (SQLException sql) {
                sql.printStackTrace();
                throw new DALException("Not able to update Event Managers", sql);
            }
        }
        else {
            try {
                String sql = "DELETE FROM EmsInEvent WHERE EventID = ?";
                String sql2 = "INSERT INTO EmsInEvent ([EventID],[EmID]) VALUES (?,?)";
                Connection connection = connectionProvider.getConnection();
                PreparedStatement st = connection.prepareStatement(sql);
                PreparedStatement st2 = connection.prepareStatement(sql2);
                for (User user : ems) {
                    st.setInt(1, event.getId());
                    st.addBatch();
                    st2.setInt(1,event.getId());
                    st2.setInt(2,user.getId());
                    st2.addBatch();
                }
                st.executeBatch();
                st2.executeBatch();
            } catch (SQLException sql) {
                sql.printStackTrace();
                throw new DALException("Not able to update Event Managers", sql);
            }
        }
    }

    public void deleteEvent(Event event)throws DALException{
        String sql = "DELETE FROM Events WHERE [ID] = ?";
        try{
            Connection connection = connectionProvider.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, event.getId());
            st.execute();
        }catch (SQLException sqlException){
            throw new DALException("Not able to delete the event",sqlException);
        }
    }
}
