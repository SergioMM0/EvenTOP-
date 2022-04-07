package dal.DAO;

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

    public List<User> getEmsInEvent(int id,List<User> allEms) throws DALException{
        List<User> emsInEvent = new ArrayList<>();
        String sql = "SELECT [EmID] FROM EmsInEvent WHERE EventID = ?";
        try{
            Connection connection = connectionProvider.getConnection();
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1,id);
            st.execute();
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                for(User user : allEms){
                    if(user.getId() == rs.getInt("EmID")){
                        emsInEvent.add(user);
                    }
                }
            }
        }catch(SQLException sqlEx){
            throw new DALException("Not able to connect to database", sqlEx);
        }
        for(User user : emsInEvent){
            System.out.println(user.getName());
        }
        return emsInEvent;
    }

}
