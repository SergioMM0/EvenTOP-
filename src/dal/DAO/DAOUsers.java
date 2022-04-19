package dal.DAO;

import be.User;
import dal.connectionProvider.ConnectionProvider;
import dal.exceptions.DALException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOUsers {

    private ConnectionProvider connectionProvider;

    public DAOUsers(){
        connectionProvider = new ConnectionProvider();
    }

    public void addUser(User user) throws DALException{
        String sql = "INSERT INTO Users([Email],[Password],[PhoneNumber],[UserType],[Name]) VALUES (?,?,?,?,?)";
        try{
            Connection connection = connectionProvider.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,user.getEmail());
            st.setString(2, user.getPassword());
            st.setString(3,user.getPhoneNumber());
            st.setString(4, user.getTypeToString());
            st.setString(5,user.getName());
            st.execute();
        }catch (SQLException dalException){
            throw new DALException("Not able to add user", dalException);
        }
    }



}
