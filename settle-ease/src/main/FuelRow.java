package main;

public class FuelRow {
    private String card;
    private String trxDate;
    private String city;
    private String state;
    private String invoiceAmount;

    public FuelRow(String card, String trxDate, String city, String state, String invoiceAmount) {
        this.card = card;
        this.trxDate = trxDate;
        this.city = city;
        this.state = state;
        this.invoiceAmount = invoiceAmount;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(String trxDate) {
        this.trxDate = trxDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

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
