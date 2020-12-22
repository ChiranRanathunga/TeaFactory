package menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeaPotConnection {
    public Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/tea_pot";
            String user = "root";
            String password = "passwordA";

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Successfully Connected to the tea pot!");
            return conn;

        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(TeaPotConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}
