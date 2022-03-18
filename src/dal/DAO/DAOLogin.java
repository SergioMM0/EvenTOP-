package dal.DAO;


import be.User;
import be.UserType;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.connectionProvider.ConnectionProvider;

import java.sql.*;

public class DAOLogin {

    ConnectionProvider connectionProvider;

    public DAOLogin(){
        connectionProvider = new ConnectionProvider();
    }

    public UserType parseType(String userType){
        UserType matched = null;
        UserType[] allTypes = UserType.values();
        for(UserType user : allTypes){
            if(user.toString().equals(userType)){
                matched = user;
            }
        }
        return matched;
    }

    public User checkCredentials(String email, String password){
        User user = null;
        String sql = "SELECT (Email, [Password], UserType FROM Users WHERE Email = ? AND [Password] = ?";
        try (Connection connection = connectionProvider.getConnection()){
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1,email);
            st.setString(2,password);
            st.execute();
            ResultSet rs = st.getResultSet();
            user = new User(parseType(rs.getString("UserType")),rs.getString("Email"),rs.getString("Password"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }


}
