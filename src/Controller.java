import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    public DatePicker tea_collection_date;
    @FXML
    public ComboBox<String> tea_col_sup_id;
    @FXML
    public ComboBox<String> tea_col_sup_id1;
    @FXML
    public TextField tea_col_Qty;
    @FXML
    public Text tea_col_sup_name;
    @FXML
    public TextField mnth_rate_yr;
    @FXML
    public ComboBox mnth_rate_month;
    @FXML
    public TextField mnth_rate_rate;
    @FXML
    public TextField waste_yr;
    @FXML
    public ComboBox waste_mnth;
    @FXML
    public TextField deduct_tea_col_Qty;

    public TitledPane GO_titledPane;
    public Button add_supplier_btn;
    public Button pay_advnc_btn;
    public Button consum_sell_btn;
    public Button other_cost_btn;
    public Button generate_report_btn;
    public Button ferti_sell_btn;
    public Button mnth_rate_set_btn;
    public Button mnth_rate_reset_btn;
    public Button deduct_tea_col_add_btn;
    public Button deduct_tea_col_reset_btn;
    public Button tea_col_add_btn;
    public Button tea_col_reset_btn;


    TeaPotConnection teaPotConnection = new TeaPotConnection();
    Connection connection = teaPotConnection.getConnection();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TSID();
    }

    private void TSID() {

        tea_col_sup_id.getItems().clear();
        tea_col_sup_id1.getItems().clear();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select TS_ID from tea_sup_reg");

            while (resultSet.next()) {
                tea_col_sup_id.getItems().addAll(resultSet.getString("TS_ID"));
                tea_col_sup_id1.getItems().addAll(resultSet.getString("TS_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SetName(ActionEvent event) {
        String supID = tea_col_sup_id.getValue();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select TS_Name from tea_sup_reg where TS_ID='" + supID + "'");

            while (resultSet.next()) {

                tea_col_sup_name.setText(resultSet.getString("TS_Name"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    ----------------Pane section for Tea Collection-------------------
    public void AddTea(ActionEvent event) {

        try {
            Statement statement = connection.createStatement();

            System.out.println(tea_collection_date.getValue());
            String sql = "INSERT INTO TEA_LEAF_COLLECTION (TL_coll_Date, quantity, TS_ID) " +
                    "VALUES ('" + tea_collection_date.getValue() + "','" + tea_col_Qty.getText() + "','" + tea_col_sup_id.getValue() + "')";

            int isAdded = statement.executeUpdate(sql);
            if (isAdded > 0) {
    AlertClass.SuccessMsg();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void Reset(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String id = btn.getId();
        if (id.equals("tea_col_reset_btn")) {

            tea_collection_date.setValue(null);
            tea_col_sup_id.setValue(null);
            tea_col_sup_name.setText(null);
            tea_col_Qty.setText(null);
        } else if (id.equals("mnth_rate_reset_btn")) {
            mnth_rate_yr.setText(null);
            mnth_rate_month.setValue(null);
            mnth_rate_rate.setText(null);
        } else if (id.equals("deduct_tea_col_reset_btn")) {
            tea_col_sup_id1.setValue(null);
            waste_yr.setText(null);
            waste_mnth.setValue(null);
            deduct_tea_col_Qty.setText(null);

        }
    }
//        ----------------End of the Pane section for Tea Collection-------------------

    //    -------------------------GO Section---------------------------
    public void AddSupplier(ActionEvent event) {
        Parent newRoot = null;
        Stage primaryStage = (Stage) tea_col_sup_name.getScene().getWindow();
        try {
            newRoot = FXMLLoader.load(getClass().getResource("supplierDetails.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.getScene().setRoot(newRoot);

    }

    public void PayAdvance(ActionEvent event) {
        Parent newRoot = null;
        Stage primaryStage = (Stage) tea_col_sup_name.getScene().getWindow();
        try {
            newRoot = FXMLLoader.load(getClass().getResource("advance.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.getScene().setRoot(newRoot);
//        G:\SrimalUnclesProject\TeaFactorySystem\src\payments\advance.fxml

    }

    public void ConsumSelling(ActionEvent event) {
        Parent newRoot = null;
        Stage primaryStage = (Stage) tea_col_sup_name.getScene().getWindow();
        try {
            newRoot = FXMLLoader.load(getClass().getResource("consumption.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.getScene().setRoot(newRoot);

    }

    public void FerSelling(ActionEvent event) {
        Parent newRoot = null;
        Stage primaryStage = (Stage) tea_col_sup_name.getScene().getWindow();
        try {
            newRoot = FXMLLoader.load(getClass().getResource("fertilizer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.getScene().setRoot(newRoot);

    }

    public void OtherCost(ActionEvent event) {
        Parent newRoot = null;
        Stage primaryStage = (Stage) tea_col_sup_name.getScene().getWindow();
        try {
            newRoot = FXMLLoader.load(getClass().getResource("otherCosts.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.getScene().setRoot(newRoot);

    }

    public void GenReport(ActionEvent event) {
        Parent newRoot = null;
        Stage primaryStage = (Stage) tea_col_sup_name.getScene().getWindow();
        try {
            newRoot = FXMLLoader.load(getClass().getResource("reportPage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.getScene().setRoot(newRoot);

    }
//-------------------------End of the GO Section---------------------------


    //-------------------------Monthly Rate Section---------------------------
    public void SetMonthRate(ActionEvent event) {
        try {
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO TEA_BUYING_RATE (month, month_rate, year) " +
                    "VALUES ('" + mnth_rate_month.getValue() + "','" + mnth_rate_rate.getText() + "','" + mnth_rate_yr.getText() + "')";

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//-------------------------Monthly Rate Section---------------------------

    public void ADDWaste(ActionEvent event) {
        try {
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO TEA_WASTE_DEDUCTION (TS_ID, wYear, wMonth,waste_quantity) " +
                    "VALUES ('" + tea_col_sup_id1.getValue() + "','" + waste_yr.getText() + "','" + waste_mnth.getValue() + " ','" + deduct_tea_col_Qty.getText() + "')";

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
