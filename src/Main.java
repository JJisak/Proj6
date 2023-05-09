import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String model3 = "model3.csv";
        String modelX = "modelX.csv";
        String modelS = "modelS.csv";

        String[] headers = {"Date", "Sales"};

        SalesReport model3SalesReport = new SalesReport(headers);
        SalesReport modelXSalesReport = new SalesReport(headers);
        SalesReport modelSSalesReport = new SalesReport(headers);

        processSalesData(model3, model3SalesReport);
        processSalesData(modelX, modelXSalesReport);
        processSalesData(modelS, modelSSalesReport);

        model3SalesReport.outputSalesReport("Model 3");
        modelXSalesReport.outputSalesReport("Model X");
        modelSSalesReport.outputSalesReport("Model S");
    }

    public static void processSalesData(String fileName, SalesReport salesReport) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String dateStr = fields[0];
                Double sales = Double.parseDouble(fields[1]);

                YearMonth yearMonth = YearMonth.parse(dateStr, DateTimeFormatter.ofPattern("MMM-yy"));
                salesReport.processSalesData(yearMonth, sales);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

}
