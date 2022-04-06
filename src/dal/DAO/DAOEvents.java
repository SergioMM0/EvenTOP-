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

    public List<Event> getAllEvents() throws DALException{
        List<Event> allEvents = new ArrayList<>();
        try{
            String sql = "SELECT * FROM Events";
            Connection connection = connectionProvider.getConnection();
            PreparedStatement st = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            st.execute();
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                return null; // IMPLEMENT FIRST TICKETS
            }
        }catch(SQLException sql){
            throw new DALException("Not able to connect to database", sql);
        }
        return null;
    }

}
