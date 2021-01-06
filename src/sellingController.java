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

public class sellingController implements Initializable {
    @FXML
    public DatePicker comsum_date;
    @FXML
    public ComboBox<String> sup_id;
    @FXML
    public TextField comsum_Qty;
    @FXML
    public TextField comsum_rate;
    @FXML
    public Text sup_name;
    @FXML
    public Text comsum_amount;
    @FXML
    public CheckBox comsum_paid;
    @FXML
    public DatePicker ferti_date;
    @FXML
    public TextField ferti_Qty;
    @FXML
    public TextField ferti_rate;
    @FXML
    public Text ferti_amount;
    @FXML
    public CheckBox ferti_paid;
    @FXML
    public DatePicker OC_date;
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TSID();
    }

    private void TSID() {
        sup_id.getItems().clear();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select TS_ID from tea_sup_reg");

            while (resultSet.next()) {
                sup_id.getItems().addAll(resultSet.getString("TS_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SetName(ActionEvent event) {
        ComboBox comboBox = (ComboBox) event.getSource();
        String supID = (String) comboBox.getValue();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select TS_Name from tea_sup_reg where TS_ID='" + supID + "'");

            while (resultSet.next()) {
                sup_name.setText(resultSet.getString("TS_Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //----------------------------------Consumption-------------------------------------
    public void AddConsumption(ActionEvent event) {
        int consumTeaAmount = Integer.parseInt(comsum_Qty.getText()) * Integer.parseInt(comsum_rate.getText());

        comsum_amount.setText(String.valueOf(consumTeaAmount));

        System.out.println(consumTeaAmount);
        if (comsum_paid.isSelected()) {
            payState = 1;
        } else payState = 0;

        try {
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO TEA_FOR_CONSUMPTION__SELLING (consum_sell_date, quantity, consum_tea_rate, consum_tea_amount, paid, TS_ID  ) " +
                    "VALUES ('" + comsum_date.getValue() + "','" + comsum_Qty.getText() + "','" + comsum_rate.getText() + "','" + consumTeaAmount + "','" + payState + "','" + sup_id.getValue() + "')";

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //----------------------------------Consumption-------------------------------------


    //----------------------------------Fertilizer-------------------------------------
    public void AddFertilizer(ActionEvent event) {
        int fertilizerAmount = Integer.parseInt(ferti_Qty.getText()) * Integer.parseInt(ferti_rate.getText());

        ferti_amount.setText(String.valueOf(fertilizerAmount));

        System.out.println(fertilizerAmount);
        if (ferti_paid.isSelected()) {
            payState = 1;
        } else payState = 0;

        try {
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO FERTILIZER_SELLING ( fer_sell_date, quantity, fer_rate, fer_amount, paid, TS_ID  ) " +
                    "VALUES ('" + ferti_date.getValue() + "','" + ferti_Qty.getText() + "','" + ferti_rate.getText() + "','" + fertilizerAmount + "','" + payState + "','" + sup_id.getValue() + "')";

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //----------------------------------Fertilizer-------------------------------------

    //----------------------------------Other Cost-------------------------------------
    public void AddOtherCost(ActionEvent event) {

        if (OC_paid.isSelected()) {
            payState = 1;
        } else payState = 0;

        try {
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO OTHER_COST (cost_type, cost_date, cost_amount, TS_ID) " +
                    "VALUES ('" + OC_type.getValue() + "','" + OC_date.getValue() + "','" + OC_amount.getText() + " ','" + sup_id.getValue() + "')";

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
            primaryStage = (Stage) comsum_Qty.getScene().getWindow();
            try {
                newRoot = FXMLLoader.load(getClass().getResource("home.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (id.equals("ferti_backToHome_btn")) {
            primaryStage = (Stage) ferti_amount.getScene().getWindow();
            try {
                newRoot = FXMLLoader.load(getClass().getResource("home.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            primaryStage = (Stage) OC_amount.getScene().getWindow();
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
            sup_id.setValue(null);
            sup_name.setText("Name");
            comsum_Qty.setText(null);
            comsum_rate.setText(null);
            comsum_amount.setText("Rs. ");
            comsum_paid.setSelected(false);
        } else if (id.equals("ferti_reset_btn")) {
            ferti_date.setValue(null);
            sup_id.setValue(null);
            sup_name.setText("Name");
            ferti_Qty.setText(null);
            ferti_rate.setText(null);
            ferti_amount.setText("Rs. ");
            ferti_paid.setSelected(false);
        } else {
            OC_date.setValue(null);
            sup_id.setValue(null);
            sup_name.setText("Name");
            OC_type.setVisible(false);
            OC_amount.setText(null);
            OC_paid.setSelected(false);
        }
    }
}
