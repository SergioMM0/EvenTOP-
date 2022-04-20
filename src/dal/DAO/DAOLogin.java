package dal.DAO;

import be.User;
import be.UserType;
import dal.connectionProvider.ConnectionProvider;
import dal.exceptions.DALException;

import java.sql.*;

public class DAOLogin {

    ConnectionProvider connectionProvider;

    public static DAOLogin instance;

    private DAOLogin(){
        connectionProvider = new ConnectionProvider();
    }

    public static DAOLogin getInstance(){
        if(instance == null){
            instance = new DAOLogin();
        }return instance;
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

    public User checkCredentials(String email, String password) throws DALException {
        User user = null;
        String sql = "SELECT Email, [Password], UserType FROM Users WHERE Email = ? AND [Password] = ?";
        try (Connection connection = connectionProvider.getConnection()){
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1,email);
            st.setString(2,password);
            st.execute();
            ResultSet rs = st.getResultSet();
            while (rs.next()){
                user = new User(parseType(rs.getString("UserType")),rs.getString("Email"),rs.getString("Password"));
            }
        } catch (SQLException throwables) {
            throw new DALException("Not able to connect to database", throwables);
        }
        return user;
    }



}
