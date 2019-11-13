package RentalPropertyManagementSystem.Client.Container;

public class Fee {

    private int ID;
    private Date paymentDate;
    private Period period;
    private double paymentAmount;


    public Date getPaymentDate() {
        return paymentDate;
    }
    public int getID() {
        return ID;
    }
    public double getPaymentAmount() {
        return paymentAmount;
    }
    public Period getPeriod() {
        return period;
    }
}
