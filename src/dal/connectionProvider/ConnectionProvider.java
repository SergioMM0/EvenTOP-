package dal.connectionProvider;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

public class ConnectionProvider {

    private SQLServerDataSource ds;

    public ConnectionProvider(){
        ds = new SQLServerDataSource();
        ds.setDatabaseName("EvenTOP");
        ds.setUser("CSe21B_29");
        ds.setPassword("CSe21B_29");
        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.31");
    }

    public Connection getConnection() throws SQLServerException {
        return ds.getConnection();
    }

}
