package configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDataBase {
    private static final String URL = "jdbc:mysql://localhost:3306/jewelry";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "codegym";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
