import javafx.beans.binding.When;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SupplierDetails {

    @FXML
    public TextField entr_sup_id;
    @FXML
    public TextField add_sup_name;
    @FXML
    public TextField add_sup_address;


    @FXML
    private TableView<supDetail> sup_detail_tbl;
    @FXML
    private TableColumn<supDetail, Integer> sup_id_clmn;
    @FXML
    private TableColumn<supDetail, String> sup_name_clmn;
    @FXML
    private TableColumn<supDetail, String> sup_add_clmn;


    private ObservableList<supDetail> detail;

    TeaPotConnection teaPotConnection = new TeaPotConnection();
    Connection connection = teaPotConnection.getConnection();

    //    ----------------------ADD Supplier Section---------------------------------
    public void AddSupplier(javafx.event.ActionEvent event) {
        try {
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO TEA_SUP_REG (TS_Name, TS_Address) " +
                    "VALUES ('" + add_sup_name.getText() + "','" + add_sup_address.getText() + "')";

            int isAdded = statement.executeUpdate(sql);
            if (isAdded > 0) {
                AlertClass.SuccessMsg();
                add_sup_name.setText(null);
                add_sup_address.setText(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    ----------------------End of the ADD Supplier Section----------------------

    //    -------------------------------Back to home -------------------------------
    public void BackToHome(ActionEvent event) {
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

    public void ViewAll(ActionEvent event) {
        detail = FXCollections.observableArrayList();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM tea_sup_reg");
            while (resultSet.next()) {
                detail.add(new supDetail(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sup_id_clmn.setCellValueFactory(new PropertyValueFactory<>("TS_id"));
        sup_name_clmn.setCellValueFactory(new PropertyValueFactory<>("TS_name"));
        sup_add_clmn.setCellValueFactory(new PropertyValueFactory<>("TS_Add"));

        sup_detail_tbl.setItems(null);
        sup_detail_tbl.setItems(detail);
    }

    public void ViewOne(ActionEvent event) {
        detail = FXCollections.observableArrayList();
        System.out.println(entr_sup_id.getText());
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM tea_sup_reg where TS_ID='" + entr_sup_id.getText() + "'");
            while (resultSet.next()) {
                detail.add(new supDetail(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sup_id_clmn.setCellValueFactory(new PropertyValueFactory<>("TS_id"));
        sup_name_clmn.setCellValueFactory(new PropertyValueFactory<>("TS_name"));
        sup_add_clmn.setCellValueFactory(new PropertyValueFactory<>("TS_Add"));

        sup_detail_tbl.setItems(null);
        sup_detail_tbl.setItems(detail);
    }
}