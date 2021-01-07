import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Month;
import java.util.ResourceBundle;

public class ReportPage implements Initializable {

    private static final Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);

    private static final Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);

    @FXML
    public ComboBox<String> single_sup_id;
    @FXML
    public Text single_sup_name;
    @FXML
    public TextField single_sup_year;
    @FXML
    public ComboBox single_sup_month;
    @FXML
    public TextField all_sup_year;
    @FXML
    public ComboBox all_sup_month;

    public String totalWeight;
    public String Waste;
    public String monthRate;
    public int TotalPrice;
    public String BillID;
    public String Advance;
    public String DrinkTea;
    public String Fertilizer;
    public String OtherCosts;
    public int Transport;

    TeaPotConnection teaPotConnection = new TeaPotConnection();
    Connection connection = teaPotConnection.getConnection();

    //    -------------------------------Back to home -------------------------------
    public void BackToHome(ActionEvent event) {
        Parent newRoot = null;
        Stage primaryStage = (Stage) single_sup_year.getScene().getWindow();
        try {
            newRoot = FXMLLoader.load(getClass().getResource("home.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.getScene().setRoot(newRoot);
    }
//    -------------------------------Back to home -------------------------------

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

    public void SingleReport(ActionEvent event) {

        String fileName = "SupID" +
                single_sup_id.getValue() + "- " +
                single_sup_month.getValue() + "-" +
                single_sup_year.getText() + ".pdf";

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

        try {
            Statement statement3 = connection.createStatement();
            ResultSet rs = statement3.executeQuery("SELECT waste_quantity FROM tea_waste_deduction where (wMonth = '" + single_sup_month.getValue() + "' AND wYear = '" + single_sup_year.getText() + "') AND TS_ID = '" + single_sup_id.getValue() + "'");
            while (rs.next()) {
                Waste = rs.getString("waste_quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Statement statement4 = connection.createStatement();
            ResultSet rs = statement4.executeQuery("SELECT month_rate FROM tea_buying_rate where (tMonth = '" + single_sup_month.getValue() + "' AND tYeae = '" + single_sup_year.getText() + "')");
            while (rs.next()) {
                monthRate = rs.getString("month_rate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        TotalPrice = (Integer.parseInt(totalWeight) - Integer.parseInt(Waste)) * Integer.parseInt(monthRate);

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            addMetaData(document);

            String para1 = "TEA FACTORY";
            String para2 = "Reg No.                  : 0000";
            String para3 = "Address                  : No.123, Main Rd, Colombo";
            String para4 = "Telephone               : 011-1234567";
            String para5 = "Supplier Name       : " + single_sup_name.getText();
            String para6 = "Total Tea Weight    :" + totalWeight + "kg";
            String para7 = "Bill No.                   : " + BillID;
            String para8 = "                                                                ";
            String para9 = "Total Amount            Rs.: " + TotalPrice;
            String para10 = "                Monthly Tea Collection Detail";

            Paragraph paragraph1 = new Paragraph(para1, subFont);
            Paragraph paragraph2 = new Paragraph(para2);
            Paragraph paragraph3 = new Paragraph(para3);
            Paragraph paragraph4 = new Paragraph(para4);
            Paragraph paragraph5 = new Paragraph(para5);
            Paragraph paragraph6 = new Paragraph(para6);
            Paragraph paragraph7 = new Paragraph(para7);
            Paragraph paragraph8 = new Paragraph(para8);
            Paragraph paragraph9 = new Paragraph(para9);
            Paragraph paragraph10 = new Paragraph(para10);

            document.add(paragraph1);
            document.add(paragraph2);
            document.add(paragraph3);
            document.add(paragraph4);
            document.add(paragraph8);

            document.add(paragraph5);
            document.add(paragraph7);
            document.add(paragraph8);

            document.add(paragraph6);
            document.add(paragraph9);
            document.add(paragraph8);
            document.add(paragraph10);
            document.add(paragraph8);


            PdfPTable table1 = new PdfPTable(2);

            PdfPCell c1 = new PdfPCell(new Phrase("Date"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(c1);

            c1 = new PdfPCell(new Phrase("Weight (kg)"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(c1);

            try {
                Statement statement3 = connection.createStatement();
                ResultSet rs2 = statement3.executeQuery("SELECT * FROM TEA_LEAF_COLLECTION where (SELECT YEAR(`TL_coll_date`)='" + single_sup_year.getText() + "' AND MONTH(`TL_coll_date`)='" + MonthNo + "') AND `TS_ID` = '" + single_sup_id.getValue() + "' ");
                while (rs2.next()) {
                    table1.addCell(rs2.getString(2));
                    table1.addCell(rs2.getString(3));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            document.add(table1);

            try {
                Statement statement5 = connection.createStatement();
                ResultSet rs = statement5.executeQuery("SELECT advance_amount FROM advance where (SELECT YEAR(`advance_date`)='" + single_sup_year.getText() + "' AND MONTH(`advance_date`)='" + MonthNo + "') AND `TS_ID` = '" + single_sup_id.getValue() + "'");
                while (rs.next()) {
                    Advance = rs.getString("advance_amount");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                Statement statement5 = connection.createStatement();
                ResultSet rs = statement5.executeQuery("SELECT consum_tea_amount FROM tea_for_consumption__selling where (SELECT YEAR(`consum_sell_date`)='" + single_sup_year.getText() + "' AND MONTH(`consum_sell_date`)='" + MonthNo + "') AND `TS_ID` = '" + single_sup_id.getValue() + "'");
                while (rs.next()) {
                    DrinkTea = rs.getString("consum_tea_amount");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                Statement statement6 = connection.createStatement();
                ResultSet rs = statement6.executeQuery("SELECT fer_amount FROM fertilizer_selling where (SELECT YEAR(`fer_sell_date`)='" + single_sup_year.getText() + "' AND MONTH(`fer_sell_date`)='" + MonthNo + "') AND `TS_ID` = '" + single_sup_id.getValue() + "'");
                while (rs.next()) {
                    Fertilizer = rs.getString("fer_amount");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Transport = Integer.parseInt(totalWeight);

            try {
                Statement statement7 = connection.createStatement();
                ResultSet rs = statement7.executeQuery("SELECT cost_amount FROM other_cost where (SELECT YEAR(`cost_date`)='" + single_sup_year.getText() + "' AND MONTH(`cost_date`)='" + MonthNo + "') AND `TS_ID` = '" + single_sup_id.getValue() + "'");
                while (rs.next()) {
                    OtherCosts = rs.getString("cost_amount");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String para11 = "Deductions";
            String para12 = "Advance   Rs.:" + Advance;
            String para13 = "Drink Tea  Rs.:" + DrinkTea;
            String para14 = "Fertilizer  Rs.:" + Fertilizer;
            String para15 = "Transport cost Rs.:" + Transport;
            String para16 = "Other costs Rs.:" + OtherCosts;

            String para17 = "Monthly Contribution : ";

            Paragraph paragraph11 = new Paragraph(para11);
            Paragraph paragraph12 = new Paragraph(para12);
            Paragraph paragraph13 = new Paragraph(para13);
            Paragraph paragraph14 = new Paragraph(para14);
            Paragraph paragraph15 = new Paragraph(para15);
            Paragraph paragraph16 = new Paragraph(para16);

            document.add(paragraph11);
            document.add(paragraph12);
            document.add(paragraph13);
            document.add(paragraph14);
            document.add(paragraph15);
            document.add(paragraph16);

            System.out.println(Advance + "   " + DrinkTea);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Report(ActionEvent event) {
        int MonthNo = monthSelector((String) all_sup_month.getValue());
        String path = all_sup_year.getText() + "-" + all_sup_month.getValue();

        try {
            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(path + ".pdf"));
            document.open();
            addMetaData(document);

            String para1 = "Monthly Report";
            String para3 = "                                ";

            // Creating Paragraphs
            Paragraph paragraph1 = new Paragraph(para1, subFont);
            Paragraph paragraph2 = new Paragraph(path);
            Paragraph paragraph3 = new Paragraph(para3);

            // Adding paragraphs to document
            document.add(paragraph1);
            document.add(paragraph2);
            document.add(paragraph3);

            PdfPTable table = new PdfPTable(11);

            PdfPCell c1 = new PdfPCell(new Phrase("Supplier ID"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Supplier Name"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Monthly Tea collection"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Monthly Tea Waste"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Fertilizer Qty"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Fer Rate"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Fer Amount"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Consumption Qty"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Consumption Rate"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Consumption Amount"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Other Costs"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            try {
                String sql = "select   tsr.ts_id, tsr.ts_name, \n" +
                        "\t\t sum(tlc.quantity) as 'tea_collection_qty', \n" +
                        "         sum(twd.waste_quantity) as 'waste_qty',\n" +
                        "         sum(f.quantity) as 'fertilizer_qty', f.fer_rate as  'fer_rate', sum(f.fer_amount) as 'fertilizer_amount',\n" +
                        "         sum(tcs.quantity) as 'consumption_qty',  tcs.consum_tea_rate as  'tea_rate', sum(tcs.consum_tea_amount) as 'consumption_amount',\n" +
                        "         sum(oc.cost_amount) as 'cost_amount'\n" +
                        "from     tea_sup_reg tsr \n" +
                        "\t\t\t\tleft outer join tea_leaf_collection tlc on tsr.TS_ID=tlc.TS_ID\n" +
                        "                left outer join tea_waste_deduction twd on tsr.TS_ID = twd.TS_ID\n" +
                        "                left outer join fertilizer_selling f on tsr.TS_ID = f.TS_ID\n" +
                        "                left outer join tea_for_consumption__selling tcs on tsr.TS_ID = tcs.TS_ID\n" +
                        "                left outer join other_cost oc on tsr.TS_ID=oc.OCost_ID\n" +
                        "where   YEAR(tlc.TL_coll_date) = " + all_sup_year.getText() + " AND MONTH(tlc.TL_coll_date) =" + MonthNo + "\n" +
                        "group by tsr.ts_id, tsr.ts_name";
                Statement statement3 = connection.createStatement();
                ResultSet rs2 = statement3.executeQuery(sql);
                while (rs2.next()) {
                    table.addCell(rs2.getString("tsr.ts_id"));
                    table.addCell(rs2.getString("tsr.ts_name"));
                    table.addCell(rs2.getString("tea_collection_qty"));
                    table.addCell(rs2.getString("waste_qty"));
                    table.addCell(rs2.getString("fertilizer_qty"));
                    table.addCell(rs2.getString("fer_rate"));
                    table.addCell(rs2.getString("fertilizer_amount"));
                    table.addCell(rs2.getString("consumption_qty"));
                    table.addCell(rs2.getString("tea_rate"));
                    table.addCell(rs2.getString("consumption_amount"));
                    table.addCell(rs2.getString("cost_amount"));


                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addMetaData(Document document) {
        document.addAuthor("CDLA");
        document.addCreator("CDLAc");
    }

    public int monthSelector(String month) {
        return Month.valueOf(month.toUpperCase()).getValue();
    }

}
