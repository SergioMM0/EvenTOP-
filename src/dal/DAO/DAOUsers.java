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


        }catch (SQLException dalException){
            throw new DALException("Not able to add user", dalException);
        }
    }



}
