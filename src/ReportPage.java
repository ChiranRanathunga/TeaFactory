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
import java.time.Month;
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

    public String totalWeight;
    public String BillID;


    public void HtmlBtn(ActionEvent event) throws IOException {
        String a = "අ";
        File f = new File("source.html");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write("<html><body><h1>" + a + "</h1>");
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

    public void SingleReport(ActionEvent event) throws IOException {
        String fileName = "SupID" + single_sup_id.getValue() + "- " + single_sup_month.getValue() + "-" + single_sup_year.getText() + ".html";

        int MonthNo = monthSelector((String) single_sup_month.getValue());
        System.out.println(MonthNo);
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT SUM(`quantity`) FROM `tea_leaf_collection` WHERE (SELECT YEAR(`TL_coll_date`)='" + single_sup_year.getText() + "' AND MONTH(`TL_coll_date`)='" + MonthNo + "') AND `TS_ID` = '" + single_sup_id.getValue() + "' ");

            String sql = "INSERT INTO bill (bill_month, bill_year, TS_ID) " +
                    "VALUES ('" + single_sup_month.getValue() + "','" + single_sup_year.getText() + "','" + single_sup_id.getValue() + "')";

            while (resultSet.next()) {
                totalWeight = resultSet.getString("SUM(`quantity`)");
            }
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {

            Statement statement2 = connection.createStatement();

            ResultSet rs = statement2.executeQuery("SELECT bill_ID FROM bill where (bill_month = '" + single_sup_month.getValue() + "' AND bill_year = '" + single_sup_year.getText() + "') AND TS_ID = '" + single_sup_id.getValue() + "'");


            while (rs.next()) {
                BillID = rs.getString("bill_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        File f = new File(fileName);
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));

        bw.write("<html><head><title>Single Supplier Report</title>");
        bw.write("<style>table, th, td {border: 1px solid black; border-collapse: collapse;}");
        bw.write("th, td {padding: 5px; text-align: left;}</style></head>");
        bw.write("<body> <h1> තේ කර්මාන්තශාලාව </h1>");
        bw.write("<h4> තේ කර්මාන්තශාලා ලියාපදිංචි අංකය : 00000 </h4>");
        bw.write("<h4> ලිපිනය </h4>");
        bw.write("<h4> දුරකතන අංකය </h4>");

        bw.write("<table>");
        bw.write("<tr>");
        bw.write("<td> සැපයුම්කරුගේ නම : " + single_sup_name.getText() + "</td>");
        bw.write("<td rowspan=\"4\"> මුළු තේ දලු එකතුව (kg) : " + totalWeight + "</td>");
        bw.write("<td rowspan=\"4\"> බිල්බත් අංකය : " + BillID + "</td> </tr>");


        bw.write("<tr><td> සැපයුම්කරුගේ අංකය : " + single_sup_id.getValue() + "</td></tr>");
        bw.write("<tr><td> මාසය : " + single_sup_month.getValue() + "</td></tr>");
        bw.write("<tr><td> වසර : " + single_sup_year.getText() + "</td></tr>");

        bw.write("<br>");

        bw.write("<table>");
        bw.write("<tr>   <tr>\n" +
                "    <th>දිනය</th>\n" +
                "    <th>බර (kg)</th>\n" +
                "    <th>දිනය</th>\n" +
                "    <th>බර (kg)</th>\n" +
                "  </tr>");
        try {

            Statement statement3 = connection.createStatement();

            ResultSet rs2 = statement3.executeQuery("SELECT * FROM TEA_LEAF_COLLECTION where (SELECT YEAR(`TL_coll_date`)='" + single_sup_year.getText() + "' AND MONTH(`TL_coll_date`)='" + MonthNo + "') AND `TS_ID` = '" + single_sup_id.getValue() + "' ");
            bw.write("<tr>");
            while (rs2.next()) {
//                BillID = rs2.getString("bill_ID");
                bw.write("<td>" +rs2.getString(2)+ "</td>");
                bw.write("<td>" +rs2.getString(3)+ "</td>");
            }
            bw.write("</tr>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        bw.write("<table> <tr><th colspan=\"2\"> අඩු කිරීම් </th>");


        bw.write("</body></html>");
        bw.close();
    }

    public int monthSelector(String month) {
        return Month.valueOf(month.toUpperCase()).getValue();
    }
}
//../TeaFactorySystem/src/reports/reportTemplate.html