package database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static DataSource DATA_SOURCE;

    public static boolean init(DataSource dataSource) {
        DATA_SOURCE = dataSource;
        try {
            dataSource.getConnection().close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Connection getConnection() throws SQLException {
            return DATA_SOURCE.getConnection();
    }

}