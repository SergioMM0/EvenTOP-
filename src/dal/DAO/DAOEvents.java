package dal.DAO;

import be.Event;
import dal.connectionProvider.ConnectionProvider;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOEvents {

    ConnectionProvider connectionProvider;

    public DAOEvents() {
        this.connectionProvider = new ConnectionProvider();
    }

    public void addEvent(Event event) throws SQLException
    {
        String sql = "INSERT INTO Events([Name],[Date],[Location],[Info]) VALUES (?,?,?,?)";
        Connection connection = connectionProvider.getConnection();
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, event.getName());
        st.setDate(2, (Date) event.getDate());
        st.setString(3, event.getLocation());
        st.setString(4, event.getInfo());
        st.execute();
    }


}
