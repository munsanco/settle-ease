package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Predicate;
import java.util.Collections;


public class FuelReport extends Report<List<FuelRow>> implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<FuelRow> fuelRows;

    public FuelReport() {
        super(new ArrayList<>());
        fuelRows = getReportData();
    }

    public void saveFuelReport(String card, String trxDate, String city, String state, String invoiceAmount) {
        FuelRow fuelRow = new FuelRow(card, trxDate, city, state, invoiceAmount);
        fuelRows.add(fuelRow);
    }

    public void saveFuelReport(FuelRow fuelRow) {
        fuelRows.add(fuelRow);
    }

    @Override
    public void saveReport() {
        System.out.println("Saving Fuel Report...");
        System.out.println("Report ID: " + getReportId());
        System.out.println("Fuel Report Rows: ");
        for (FuelRow fuelRow : fuelRows) {
            System.out.println(fuelRow);
        }
    }

    @Override
    public void process() {
        System.out.println("Processing Fuel Report...");
        System.out.println("Report ID: " + getReportId());
    }

    public List<FuelRow> getFuelRows() {
        return fuelRows;
    }

    public void setFuelRows(List<FuelRow> fuelRows) {
        this.fuelRows = fuelRows;
    }

    public Stream<FuelRow> getFuelRowStream() {
        return fuelRows.stream();
    }

    public double calculateTotalFuelSpent(String fuelCardNumber) {
        Predicate<FuelRow> filter = fuelRow -> fuelRow.getCard().equals(fuelCardNumber);

        return getFuelRowStream()
                .filter(filter)
                .mapToDouble(fuelRow -> Double.parseDouble(fuelRow.getInvoiceAmount()))
                .sum();
    }


    public boolean isValidFuelCard(String fuelCardNumber) {
        Predicate<FuelRow> filter = fuelRow ->
                fuelRow.getCard().equals(fuelCardNumber);

        return getFuelRowStream()
                .anyMatch(filter);
    }

    public List<FuelRow> filterByState(String state) {
        return getFuelRowStream()
                .filter(fuelRow -> fuelRow.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }

    public List<FuelRow> filterByFuelCardNumber(String fuelCardNumber) {
        return getFuelRowStream()
                .filter(fuelRow -> fuelRow.getCard().equals(fuelCardNumber))
                .collect(Collectors.toList());
    }

    public void processFuelRows(Predicate<FuelRow> filter) {
        System.out.println("Processing Fuel Report Rows...");
        getFuelRowStream()
                .filter(filter)
                .forEach(System.out::println);
    }

    public int getFuelCardRank(String fuelCardNumber) {
        Map<String, Double> totalFuelSpentByCard = fuelRows.stream()
                .collect(Collectors.groupingBy(
                        fuelRow -> fuelRow.getCard(),
                        Collectors.summingDouble(fuelRow -> Double.parseDouble(fuelRow.getInvoiceAmount()))
                ));

        List<Double> totalFuelSpentValues = new ArrayList<>(totalFuelSpentByCard.values());
        Collections.sort(totalFuelSpentValues, Collections.reverseOrder());

        int rank = 1;
        for (Double totalFuelSpent : totalFuelSpentValues) {
            if (Double.parseDouble(totalFuelSpentByCard.get(fuelCardNumber).toString()) >= totalFuelSpent) {
                break;
            }
            rank++;
        }

        return rank;
    }

}
