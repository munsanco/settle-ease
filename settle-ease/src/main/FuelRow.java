package main;

public class FuelRow {
    private String card;
    private String trxDate;
    private String city;
    private String state;
    private String invoiceAmount;
    

    /**
     * Constructs a FuelRow object with the specified parameters.
     *
     * @param card           the fuel card number
     * @param trxDate        the transaction date
     * @param city           the city
     * @param state          the state
     * @param invoiceAmount  the invoice amount
     */

    public FuelRow(String card, String trxDate, String city, String state, String invoiceAmount) {
        this.card = card;
        this.trxDate = trxDate;
        this.city = city;
        this.state = state;
        this.invoiceAmount = invoiceAmount;
    }
    // Retrieves the fuel card number.
    public String getCard() {
        return card;
    }
    // Sets the fuel card number.
    public void setCard(String card) {
        this.card = card;
    }
    // Retrieves the transaction date.
    public String getTrxDate() {
        return trxDate;
    }
    // Sets the transaction date.
    public void setTrxDate(String trxDate) {
        this.trxDate = trxDate;
    }
    // Retrieves the city.
    public String getCity() {
        return city;
    }
    // Sets the city.
    public void setCity(String city) {
        this.city = city;
    }
    // Retrieves the state
    public String getState() {
        return state;
    }
    // Sets the state
    public void setState(String state) {
        this.state = state;
    }
    // Retrieves the invoice amount
    public String getInvoiceAmount() {
        return invoiceAmount;
    }
    // Sets the invoice amount
    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    @Override
    public String toString() {
        return "FuelRow{" +
                "card='" + card + '\'' +
                ", trxDate='" + trxDate + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", invoiceAmount='" + invoiceAmount + '\'' +
                '}';
    }
}
