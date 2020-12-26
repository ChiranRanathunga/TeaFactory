import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class AlertClass {
    public static void SuccessMsg() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success...!");
        alert.setHeaderText(null);
        alert.setContentText("Added Successfully.....!");
        alert.showAndWait();
    }

    public static void DismatchMsg() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Wrong User Name or Password...!");
        alert.setHeaderText(null);
        alert.setContentText("Enter correct User Name & Password!");
        alert.showAndWait();
    }

    public static void NullMsg() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cannot be Empty...!");
        alert.setHeaderText(null);
        alert.setContentText("Make sure you have entered ALL details...");
        alert.showAndWait();
    }

    public static void OnlyInt() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Only Integers...!");
        alert.setHeaderText(null);
        alert.setContentText("please do not insert,\n   -> Caractors\n   -> Symbols\nInsert Only Numbers!");
        alert.showAndWait();
    }

    public static void LowQnt(int userQnt, int storeQnt) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Insufficient Quantity!");
        alert.setHeaderText(null);
        alert.setContentText(userQnt + " it too much...!\nWe have only " + storeQnt);
        alert.showAndWait();
    }

//    static void confirmFrmUser(String dltID) {
//        DBpharmacysys dBpharmacysys = new DBpharmacysys();
//        Connection connection = dBpharmacysys.getConnection();
//
//        try {
//            Statement statement = connection.createStatement();
//
//            String sqlDlt = "DELETE FROM item_master WHERE item_id =" + Integer.parseInt(dltID);
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Please Confirm...!");
//            alert.setHeaderText(null);
//            alert.setContentText("Are you Sure you want to DELETE this" + dltID + "?");
//            Optional<ButtonType> result = alert.showAndWait();
//            if (result.get() == ButtonType.OK) {
//                statement.executeUpdate(sqlDlt);
//            } else {
//                alert.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//    }
}
