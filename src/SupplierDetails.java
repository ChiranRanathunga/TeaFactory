import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import TeaPotConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class SupplierDetails {

    @FXML
    public TextField add_sup_id;
    @FXML
    public TextField add_sup_name;
    @FXML
    public TextField add_sup_address;


    TeaPotConnection teaPotConnection = new TeaPotConnection();
    Connection connection = teaPotConnection.getConnection();

    //    ----------------------ADD Supplier Section---------------------------------
    public void AddSupplier(javafx.event.ActionEvent event) {
        try {
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO TEA_SUP_REG (TS_ID, TS_Name, TS_Address) " +
                    "VALUES ('" + add_sup_id.getText() + "','" + add_sup_name.getText() + "','" + add_sup_address.getText() + "')";

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    ----------------------End of the ADD Supplier Section----------------------

//    -------------------------------Back to home -------------------------------
    public void BackToHome(ActionEvent event){
        Parent newRoot = null;
        Stage primaryStage = (Stage) add_sup_name.getScene().getWindow();
        try {
            newRoot = FXMLLoader.load(getClass().getResource("home.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.getScene().setRoot(newRoot);
    }
//    -------------------------------Back to home -------------------------------
}
