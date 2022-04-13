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
            st.setString(5,event.getStartTime());
            st.setString(6,event.getEndTime());
            st.execute();
        } catch (SQLException sqlException) {
            throw new DALException("Not connected to database",sqlException);
        }
        //TODO in BLL check that event doesn't have same name
    }

    public void addEventAndEMs(Event event, List<User> ems) throws DALException{
        try{
            addEvent(event);
            String sql = "SELECT [ID] FROM Events WHERE [Name] = ?";
            Connection connection = connectionProvider.getConnection();
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1,event.getName());
            st.execute();
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                event.setId(rs.getInt("ID"));
            }
        }
        catch (SQLException sqlException) {
            throw new DALException("Not able to connect to database",sqlException);
        }
        for(User user : ems){
            try{
                String sql = "SELECT [ID] FROM Users WHERE [Name] = ?";
                Connection connection = connectionProvider.getConnection();
                PreparedStatement st = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                st.setString(1,user.getName());
                st.execute();
                ResultSet rs = st.getResultSet();
                while(rs.next()){
                    user.setId(rs.getInt("ID"));
                }
                try{
                    String sql2 = "INSERT INTO [EmsInEvent] (EventId,EmID) VALUES (?,?)";
                    Connection connection2 = connectionProvider.getConnection();
                    PreparedStatement st2 = connection2.prepareStatement(sql2);
                    st2.setInt(1,event.getId());
                    st2.setInt(2,user.getId());
                    st2.execute();
                }
                catch(SQLException sql2){
                    sql2.printStackTrace();
                    throw new DALException("Not able to connect to database",sql2);
                }
            }
            catch(SQLException sqlException){
                throw new DALException("Not able to connect to database",sqlException);
            }
        }
    }

    private java.util.Date parseDate(Date sqlDate){
        return new Date(sqlDate.getTime());
    }

    public List<Event> getAllEvents() throws DALException{
        List<Event> allEvents = new ArrayList<>();
        try{
            String sql = "SELECT [ID],[Name],[Date],[Location],[Info] FROM Events ";
            Connection connection = connectionProvider.getConnection();
            PreparedStatement st = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            st.execute();
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                String ems = null;
                int tickets = 0;
                Event event = new Event(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        parseDate(rs.getDate("Date")),
                        ems,
                        rs.getString("Location"),
                        tickets,
                        rs.getString("Info")
                );
                try{
                    String sql2 = "SELECT [Name] FROM Users WHERE Users.ID IN (SELECT [EmID] FROM EmsInEvent WHERE EventID = ?)";
                    Connection connection1 = connectionProvider.getConnection();
                    PreparedStatement st2 = connection1.prepareStatement(sql2,Statement.RETURN_GENERATED_KEYS);
                    st2.setInt(1,event.getId());
                    st2.execute();
                    ResultSet rs2 = st2.getResultSet();
                    while(rs2.next()){
                        event.addEventManager(rs2.getString("Name"));
                    }
                }catch (SQLException sqlException2){
                    throw new DALException("Error getting the EventManagers",sqlException2);
                }
                try{
                    String sql3 = "SELECT COUNT([BARCODE]) AS NumberOfTickets FROM Tickets WHERE Tickets.BARCODE IN (SELECT [BARCODE] FROM TicketsInEvent WHERE EVENTID = ?)";
                    Connection connection2 = connectionProvider.getConnection();
                    PreparedStatement st3 = connection2.prepareStatement(sql3,Statement.RETURN_GENERATED_KEYS);
                    st3.setInt(1,event.getId());
                    st3.execute();
                    ResultSet rs3 = st3.getResultSet();
                    while(rs3.next()){
                        tickets = rs3.getInt("NumberOfTickets");
                        event.setTickets(tickets);
                    }
                }catch (SQLException sqlException3) {
                    throw new DALException("Error getting the number of tickets", sqlException3);
                }
                allEvents.add(event);
            }

        }catch(SQLException sqlException){
            throw new DALException("Not able to connect to database",sqlException);
        }
        return allEvents;
    }

}
