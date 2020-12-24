import javafx.event.ActionEvent;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReportPage {
    public void HtmlBtn(ActionEvent event) throws IOException {
        File f = new File("source.html");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write("<html><body><h1>Blah, Blah!</h1>");
        bw.write("<textarea cols=75 rows=10>");

        bw.write("</textarea>");
        bw.write("</body></html>");
        bw.close();

//        Desktop.getDesktop().browse(f.toURI());
    }

}
//../TeaFactorySystem/src/reports/reportTemplate.html