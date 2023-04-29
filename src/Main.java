import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        String model3 = "model3.csv";
        String modelS = "modelS.csv";
        String modelX = "modelX.csv";
        String[] headers = { "Date", "Sales" };
        SalesReport salesReport = new SalesReport(headers);

        try (BufferedReader br = new BufferedReader(new FileReader(model3))) {
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

        salesReport.outputSalesReport();
    }
}
