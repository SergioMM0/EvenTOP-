package dal.DAO;

import be.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.connectionProvider.ConnectionProvider;
import dal.exceptions.DALException;

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
        String sql = "SELECT [Name] FROM Users WHERE UserType = 'EVENTMANAGER'";
        try(Connection conn = connectionProvider.getConnection()){
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.execute();
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                allUsers.add(new User(rs.getString("Name")));
            }
        }
        catch (SQLException sqlException) {
            throw new DALException("Not connected to database", sqlException);
        }
        return allUsers;
    }

}
