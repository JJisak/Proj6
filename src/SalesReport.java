import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class SalesReport {
    private String[] headers;
    private Map<Year, Double> yearlySales = new HashMap();
    private Map<YearMonth, Double> monthlySales = new HashMap();
    private Double bestMonthSales = Double.NEGATIVE_INFINITY;
    private YearMonth bestMonth = null;
    private Double worstMonthSales = Double.POSITIVE_INFINITY;
    private YearMonth worstMonth = null;

    public SalesReport(String[] headers) {
        this.headers = headers;
    }

    public void processSalesData(YearMonth yearMonth, Double sales) {
        Year year = Year.of(yearMonth.getYear());
        this.yearlySales.merge(year, sales, Double::sum);
        this.monthlySales.merge(yearMonth, sales, Double::sum);
        if (sales > this.bestMonthSales) {
            this.bestMonthSales = sales;
            this.bestMonth = yearMonth;
        }

        if (sales < this.worstMonthSales) {
            this.worstMonthSales = sales;
            this.worstMonth = yearMonth;
        }
    }



    public void outputSalesReport(String modelName) {
        System.out.println(modelName + " Yearly Sales Report");
        System.out.println("---------------------------");

        Iterator var1 = this.yearlySales.keySet().iterator();

        while(var1.hasNext()) {
            Year year = (Year)var1.next();
            System.out.printf("%d -> %.2f%n", year.getValue(), this.yearlySales.get(year));
        }

        System.out.println();
        System.out.printf("The best month for " + modelName + " was: %s%n", this.bestMonth.format(DateTimeFormatter.ofPattern("yyyy-MM")));
        System.out.printf("The worst month for " + modelName + " was: %s%n", this.worstMonth.format(DateTimeFormatter.ofPattern("yyyy-MM")));
        System.out.println();

    }
}

