package dal.DAO;

import be.Event;
import be.User;
import dal.connectionProvider.ConnectionProvider;
import dal.exceptions.DALException;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOEventManagers {


    ConnectionProvider connectionProvider;

    public DAOEventManagers() {
        this.connectionProvider = new ConnectionProvider();
    }

    public List<User> getAllEms() throws DALException {
        List<User> allUsers = new ArrayList<>();
        String sql = "SELECT [Name],[ID] FROM Users WHERE UserType = 'EVENTMANAGER'";
        try(Connection conn = connectionProvider.getConnection()){
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.execute();
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                allUsers.add(new User(rs.getInt("ID"),rs.getString("Name")));
            }
        }
        catch (SQLException sqlException) {
            throw new DALException("Not able to connect to database", sqlException);
        }
        return allUsers;
    }

    public List<User> getEmsInEvent(Event event) throws DALException{
        List<User> allEms = new ArrayList<>();
        try{
            String sql = "SELECT [ID],[Name] FROM Users WHERE UserType = 'EVENTMANAGER' AND Users.ID IN(SELECT EmId FROM EmsInEvent WHERE EventID = ?)";
            Connection connection = connectionProvider.getConnection();
            PreparedStatement st = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            st.setInt(1,event.getId());
            st.execute();
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                allEms.add(
                        new User(
                                rs.getInt("ID"),
                                rs.getString("Name")));
            }

        }catch(SQLException sqlException){
            throw new DALException("Not able to connect to database",sqlException);
        }
        return allEms;
    }
}
