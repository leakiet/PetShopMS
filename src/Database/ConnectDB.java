package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    Connection con = null;

    public Connection GetConnectDB() {
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=test1;";
            String user = "sa";
            String password = "1";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connect successfully");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Cannot connect !" + e.getMessage());
        }
        return con;
    }
}
