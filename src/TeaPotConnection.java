import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeaPotConnection {
    public Connection getConnection() {
        try {
            String url = "jdbc:mysql://127.0.0.1/tea_pot_db";
            String user = "root";
            String password = "passwordA";

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Successfully Connected to the tea pot!");

//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Test Connection");
//            alert.setHeaderText("Results:");
//            alert.setContentText("Connect to the database successfully!");
//            alert.showAndWait();

            return conn;

        } catch (ClassNotFoundException | SQLException e) {


            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error alert");
            alert.setHeaderText(e.getMessage());

            VBox dialogPaneContent = new VBox();

            Label label = new Label("Stack Trace:");

            String stackTrace = this.getStackTrace(e);
            TextArea textArea = new TextArea();
            textArea.setText(stackTrace);

            dialogPaneContent.getChildren().addAll(label, textArea);

            alert.getDialogPane().setContent(dialogPaneContent);

            alert.showAndWait();
            Logger.getLogger(TeaPotConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    private String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String s = sw.toString();
        return s;
    }
}
