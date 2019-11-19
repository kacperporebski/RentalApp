package RentalPropertyManagementSystem.Client.Container;

public class Fee {
    private Date paymentDate;
    private Period period;
    private double paymentAmount;
    static private int ID = 0;


    public Fee(double pay){
        ID++;
        paymentAmount = pay;
        paymentDate = new Date();
        period = new Period();
        period.setStartDate(paymentDate);
        Date endDate = getPaymentDate();
        endDate.set_month(getPaymentDate().get_month() + 2);
        period.setEndDate(endDate);
    }

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
