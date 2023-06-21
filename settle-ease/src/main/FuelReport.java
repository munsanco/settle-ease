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
    /**
     * Constructs a FuelReport object.
     * Initializes the fuelRows list with an empty ArrayList.
     */
    public FuelReport() {
        super(new ArrayList<>());
        fuelRows = getReportData();
    }
    /**
     * Saves a fuel report with the specified information.
     *
     * @param card           the fuel card number
     * @param trxDate        the transaction date
     * @param city           the city
     * @param state          the state
     * @param invoiceAmount  the invoice amount
     */
    public void saveFuelReport(String card, String trxDate, String city, String state, String invoiceAmount) {
    	// Postcondition: a new FuelRow is added to the fuelRows list
    	FuelRow fuelRow = new FuelRow(card, trxDate, city, state, invoiceAmount);
        fuelRows.add(fuelRow);
        /*
         Saves a fuel report with the specified FuelRow object.
         
          @param fuelRow  the FuelRow object representing a fuel report
         */
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
    //Calculates the total fuel spent for the given fuel card number.
    public double calculateTotalFuelSpent(String fuelCardNumber) {
    	// Precondition: fuelCardNumber is not null
        Predicate<FuelRow> filter = fuelRow -> fuelRow.getCard().equals(fuelCardNumber);

        return getFuelRowStream()
                .filter(filter)
                .mapToDouble(fuelRow -> Double.parseDouble(fuelRow.getInvoiceAmount()))
                .sum();
    }


    public boolean isValidFuelCard(String fuelCardNumber) {
    	// Precondition: fuelCardNumber is not null
        Predicate<FuelRow> filter = fuelRow ->
                fuelRow.getCard().equals(fuelCardNumber);

        return getFuelRowStream()
                .anyMatch(filter);
    }

    public List<FuelRow> filterByState(String state) {
    	// Precondition: state is not null
        return getFuelRowStream()
                .filter(fuelRow -> fuelRow.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }

    public List<FuelRow> filterByFuelCardNumber(String fuelCardNumber) {
    	// Precondition: fuelCardNumber is not null
        return getFuelRowStream()
        		//Processes the fuel rows based on the specified filter.
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
    	// Precondition: fuelCardNumber is not null
        Map<String, Double> totalFuelSpentByCard = fuelRows.stream()
                .collect(Collectors.groupingBy(
                        fuelRow -> fuelRow.getCard(),
                        Collectors.summingDouble(fuelRow -> Double.parseDouble(fuelRow.getInvoiceAmount()))
                ));
        // Calculate total fuel spent for each fuel card
        List<Double> totalFuelSpentValues = new ArrayList<>(totalFuelSpentByCard.values());
        Collections.sort(totalFuelSpentValues, Collections.reverseOrder());
        // Sort the total fuel spent values in descending order
        int rank = 1;
        for (Double totalFuelSpent : totalFuelSpentValues) {
        	// Iterate through the sorted total fuel spent values
            if (Double.parseDouble(totalFuelSpentByCard.get(fuelCardNumber).toString()) >= totalFuelSpent) {
                break;
            // Check if the total fuel spent for the given fuel card is greater than or equal to the current value
            // If so, exit the loop and keep the current rank
            }
            rank++;
        }
        	// Calculate the rank of the given fuel card based on total fuel spent

        return rank;
    }

}
