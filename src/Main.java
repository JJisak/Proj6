import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {
        String model3 = "model3.csv";
        String modelX = "modelX.csv";
        String modelS = "modelS.csv";

        String[] headers = { "Date", "Sales" };

        SalesReport model3SalesReport = new SalesReport(headers);
        SalesReport modelXSalesReport = new SalesReport(headers);
        SalesReport modelSSalesReport = new SalesReport(headers);

        processSalesData(model3, model3SalesReport);
        processSalesData(modelX, modelXSalesReport);
        processSalesData(modelS, modelSSalesReport);

        outputSalesReport("Model 3", model3SalesReport);
        outputSalesReport("Model X", modelXSalesReport);
        outputSalesReport("Model S", modelSSalesReport);
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

    public static void outputSalesReport(String modelName, SalesReport salesReport) {
        System.out.println(modelName + " Yearly Sales Report");
        System.out.println("---------------------------");
        salesReport.outputSalesReport();
        System.out.println();
    }
}
