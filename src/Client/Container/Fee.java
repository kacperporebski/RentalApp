package Client.Container;

public class Fee {

    private int ID;
    private Date paymentDate;
    private Period period;
    private double paymentAmount;


    public Fee(double pay){
        paymentAmount = pay;
        paymentDate = new Date();
        //TODO period and ID

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
