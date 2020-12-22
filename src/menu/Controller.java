package menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class Controller {
//    public void

    @FXML
    public DatePicker tea_collection_date;
    @FXML
    public TextField tea_col_sup_id;
    @FXML
    public TextField tea_col_Qty;
    @FXML
    public Text tea_col_sup_name;
    @FXML
    public TextField mnth_rate_yr;
    @FXML
    public TextField mnth_rate_month;
    @FXML
    public TextField mnth_rate_rate;
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

    //    ----------------Pane section for Tea Collection-------------------
    public void AddTea(ActionEvent event) {
        try {
            Statement statement = connection.createStatement();


            System.out.println(tea_collection_date.getValue());
            String sql = "INSERT INTO TEA_LEAF_COLLECTION (TL_coll_Date, quantity, TS_ID) " +
                    "VALUES ('" + tea_collection_date.getValue() + "','" + tea_col_Qty.getText() + "','" + tea_col_sup_id.getText() + "')";

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void Reset(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String id = btn.getId();
        if (id.equals("tea_col_reset_btn")) {

            tea_collection_date.setValue(null);
            tea_col_sup_id.setText(null);
            tea_col_sup_name.setText(null);
            tea_col_Qty.setText(null);
        } else if (id.equals("mnth_rate_reset_btn")) {
            mnth_rate_yr.setText(null);
            mnth_rate_month.setText(null);
            mnth_rate_rate.setText(null);
        } else {
        }
    }
//        ----------------End of the Pane section for Tea Collection-------------------

    //    -------------------------GO Section---------------------------
    public void AddSupplier(ActionEvent event) {
        Parent newRoot = null;
        Stage primaryStage = (Stage) tea_col_sup_name.getScene().getWindow();
        try {
            newRoot = FXMLLoader.load(getClass().getResource("../suppliers/supplierDetails.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.getScene().setRoot(newRoot);

    }

    public void PayAdvance(ActionEvent event) {
        Parent newRoot = null;
        Stage primaryStage = (Stage) tea_col_sup_name.getScene().getWindow();
        try {
            newRoot = FXMLLoader.load(getClass().getResource("G:/SrimalUnclesProject/TeaFactorySystem/src/payments/advance.fxml"));
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
            newRoot = FXMLLoader.load(getClass().getResource("../sellings/consumption.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.getScene().setRoot(newRoot);

    }

    public void FerSelling(ActionEvent event) {
        Parent newRoot = null;
        Stage primaryStage = (Stage) tea_col_sup_name.getScene().getWindow();
        try {
            newRoot = FXMLLoader.load(getClass().getResource("../sellings/fertilizer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.getScene().setRoot(newRoot);

    }

    public void OtherCost(ActionEvent event) {
        Parent newRoot = null;
        Stage primaryStage = (Stage) tea_col_sup_name.getScene().getWindow();
        try {
            newRoot = FXMLLoader.load(getClass().getResource("../sellings/otherCosts.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.getScene().setRoot(newRoot);

    }

    public void GenReport(ActionEvent event) {
        Parent newRoot = null;
        Stage primaryStage = (Stage) tea_col_sup_name.getScene().getWindow();
        try {
            newRoot = FXMLLoader.load(getClass().getResource("../reports/reportPage.fxml"));
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
                    "VALUES ('" + mnth_rate_month.getText() + "','" + mnth_rate_rate.getText() + "','" + mnth_rate_yr.getText() + "')";

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//-------------------------Monthly Rate Section---------------------------

}
