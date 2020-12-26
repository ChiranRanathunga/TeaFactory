import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Pay implements Initializable {
    @FXML
    public ComboBox<String> advnc_sup_id;
    @FXML
    public TextField advnc_sup_amount;
    @FXML
    public Text advnc_sup_name;
    @FXML
    public DatePicker advnc_sup_date;

    TeaPotConnection teaPotConnection = new TeaPotConnection();
    Connection connection = teaPotConnection.getConnection();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TSID();
    }

    private void TSID() {

        advnc_sup_id.getItems().clear();


        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select TS_ID from tea_sup_reg");

            while (resultSet.next()) {
                advnc_sup_id.getItems().addAll(resultSet.getString("TS_ID"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SetName(ActionEvent event) {
        String supID = advnc_sup_id.getValue();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select TS_Name from tea_sup_reg where TS_ID='" + supID + "'");

            while (resultSet.next()) {

                advnc_sup_name.setText(resultSet.getString("TS_Name"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AdvancePay(ActionEvent event) {
        try {
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO ADVANCE (advance_date, advance_amount, TS_ID) " +
                    "VALUES ('" + advnc_sup_date.getValue() + "','" + advnc_sup_amount.getText() + "','" + advnc_sup_id.getValue() + "')";

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
