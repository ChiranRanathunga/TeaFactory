import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//import TeaPotConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class sellingController {
    @FXML
    public DatePicker comsum_date;
    @FXML
    public TextField comsum_sup_id;
    @FXML
    public TextField comsum_Qty;
    @FXML
    public TextField comsum_rate;
    @FXML
    public Text comsum_sup_name;
    @FXML
    public Text comsum_amount;
    @FXML
    public CheckBox comsum_paid;
    @FXML
    public DatePicker ferti_date;
    @FXML
    public TextField ferti_sup_id;
    @FXML
    public TextField ferti_Qty;
    @FXML
    public TextField ferti_rate;
    @FXML
    public Text ferti_name;
    @FXML
    public Text ferti_amount;
    @FXML
    public CheckBox ferti_paid;
    @FXML
    public DatePicker OC_date;
    @FXML
    public TextField OC_sup_id;
    @FXML
    public Text OC_sup_name;
    @FXML
    public ComboBox OC_type;
    @FXML
    public TextField OC_amount;
    @FXML
    public CheckBox OC_paid;
    public Button comsum_add_btn;
    public Button comsum_reset_btn;
    public Button comsum_bckToHome;
    public Button ferti_add_btn;
    public Button ferti_reset_btn;
    public Button ferti_backToHome_btn;
    public Button OC_add_btn;
    public Button OC_reset_btn;
    public Button OC_backtoHm_btn;

    TeaPotConnection teaPotConnection = new TeaPotConnection();
    Connection connection = teaPotConnection.getConnection();
    int payState;

    //----------------------------------Consumption-------------------------------------
    public void AddConsumption(ActionEvent event) {
        int consumTeaAmount = Integer.parseInt(comsum_Qty.getText()) * Integer.parseInt(comsum_rate.getText());

        System.out.println(consumTeaAmount);
        if (comsum_paid.isSelected()) {
            payState = 1;
        } else payState = 0;


        try {
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO TEA_FOR_CONSUMPTION_SELLING (TS_ID, consum_sell_date, quantity, consum_tea_rate, consum_tea_amount, paid  ) " +
                    "VALUES ('" + comsum_sup_id.getText() + "','" + comsum_date.getValue() + "','" + comsum_Qty.getText() + "','" + comsum_rate.getText() + "','" + consumTeaAmount + "','" + payState + "')";

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //----------------------------------Consumption-------------------------------------


    //----------------------------------Fertilizer-------------------------------------
    public void AddFertilizer(ActionEvent event) {
        int fertilizerAmount = Integer.parseInt(ferti_Qty.getText()) * Integer.parseInt(ferti_rate.getText());

        System.out.println(fertilizerAmount);
        if (ferti_paid.isSelected()) {
            payState = 1;
        } else payState = 0;


        try {
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO FERTILIZER_SELLING (TS_ID, fer_sell_date, quantity, fer_rate, fer_amount, paid  ) " +
                    "VALUES ('" + ferti_sup_id.getText() + "','" + ferti_date.getValue() + "','" + ferti_Qty.getText() + "','" + ferti_rate.getText() + "','" + fertilizerAmount + "','" + payState + "')";

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //----------------------------------Fertilizer-------------------------------------

    //----------------------------------Other Cost-------------------------------------
    public void AddOtherCost(ActionEvent event) {
//        System.out.println(fertilizerAmount);
        if (OC_paid.isSelected()) {
            payState = 1;
        } else payState = 0;

        try {
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO OTHER_COST (cost_type, cost_date, TS_ID, amount) " +
                    "VALUES ('" + OC_type.getValue() + "','" + OC_date.getValue() + "','" + OC_sup_id.getText() + "','" + OC_amount.getText() + "')";

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //----------------------------------Fertilizer-------------------------------------


    //    -------------------------------Back to home -------------------------------
    public void BackToHome(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String id = btn.getId();
        Parent newRoot = null;
        Stage primaryStage;
        if (id.equals("comsum_bckToHome")) {
            primaryStage = (Stage) comsum_sup_id.getScene().getWindow();
            try {
                newRoot = FXMLLoader.load(getClass().getResource("home.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            primaryStage = (Stage) ferti_name.getScene().getWindow();
            try {
                newRoot = FXMLLoader.load(getClass().getResource("home.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        primaryStage.getScene().setRoot(newRoot);

    }
//    -------------------------------Back to home -------------------------------

    public void Reset(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String id = btn.getId();
        if (id.equals("comsum_reset_btn")) {
            comsum_date.setValue(null);
            comsum_sup_id.setText(null);
            comsum_sup_name.setText("Name");
            comsum_Qty.setText(null);
            comsum_rate.setText(null);
            comsum_amount.setText("Rs. ");
            comsum_paid.setSelected(false);
        } else if (id.equals("ferti_reset_btn")) {
            ferti_date.setValue(null);
            ferti_sup_id.setText(null);
            ferti_name.setText("Name");
            ferti_Qty.setText(null);
            ferti_rate.setText(null);
            ferti_amount.setText("Rs. ");
            ferti_paid.setSelected(false);
        } else {
            OC_date.setValue(null);
            OC_sup_id.setText(null);
            OC_sup_name.setText("Name");
            OC_type.setVisible(false);
            OC_amount.setText(null);
            OC_paid.setSelected(false);
        }
    }

}
