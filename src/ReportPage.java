import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ReportPage implements Initializable {

    @FXML
    public ComboBox<String> single_sup_id;
    @FXML
    public Text single_sup_name;
    @FXML
    public TextField single_sup_year;
    @FXML
    public ComboBox single_sup_month;


    public void HtmlBtn(ActionEvent event) throws IOException {
        String a = "අ";
        File f = new File("source.html");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write("<html><body><h1>"+a+"</h1>");
        bw.write("<textarea cols=75 rows=10>");

        bw.write("</textarea>");
        bw.write("</body></html>");
        bw.close();

//        Desktop.getDesktop().browse(f.toURI());
    }

    TeaPotConnection teaPotConnection = new TeaPotConnection();
    Connection connection = teaPotConnection.getConnection();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TSID();
    }

    private void TSID() {

        single_sup_id.getItems().clear();


        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select TS_ID from tea_sup_reg");

            while (resultSet.next()) {
                single_sup_id.getItems().addAll(resultSet.getString("TS_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SetName(ActionEvent event) {
        String supID = single_sup_id.getValue();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select TS_Name from tea_sup_reg where TS_ID='" + supID + "'");

            while (resultSet.next()) {

                single_sup_name.setText(resultSet.getString("TS_Name"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SingleReport(ActionEvent event){

    }

}
//../TeaFactorySystem/src/reports/reportTemplate.html