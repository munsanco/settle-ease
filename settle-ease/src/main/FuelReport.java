package main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.Serializable;

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

    public double calculateTotalInvoiceAmount(String fuelCardNumber) {
        return getFuelRowStream()
                .filter(fuelRow -> fuelRow.getCard().equals(fuelCardNumber))
                .mapToDouble(fuelRow -> Double.parseDouble(fuelRow.getInvoiceAmount()))
                .sum();
    }

    public List<FuelRow> filterByState(String state) {
        return getFuelRowStream()
                .filter(fuelRow -> fuelRow.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }
}

