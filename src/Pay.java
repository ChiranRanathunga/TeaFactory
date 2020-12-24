import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//import TeaPotConnection;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Pay {
    @FXML
    public TextField advnc_sup_id;
    @FXML
    public TextField advnc_sup_amount;
    @FXML
    public DatePicker advnc_sup_date;

    TeaPotConnection teaPotConnection = new TeaPotConnection();
    Connection connection = teaPotConnection.getConnection();

    public void AdvancePay(ActionEvent event) {
        try {
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO ADVANCE (TS_ID, advnc_date, advnc_amount) " +
                    "VALUES ('" + advnc_sup_id.getText() + "','" + advnc_sup_date.getValue() + "','" + advnc_sup_amount.getText() + "')";

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    -------------------------------Back to home -------------------------------
    public void BackToHome(ActionEvent event) {
        Parent newRoot = null;
        Stage primaryStage = (Stage) advnc_sup_id.getScene().getWindow();
        try {
            newRoot = FXMLLoader.load(getClass().getResource("home.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.getScene().setRoot(newRoot);
    }
//    -------------------------------Back to home -------------------------------
}
