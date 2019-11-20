package RentalPropertyManagementSystem.Client.Container;

public class Fee {
    private Date paymentDate;
    private Period period;
    private double paymentAmount;
    static private int ID = 0;
    private boolean paid;


    public Fee(double pay)
    {
        paid = false;
        ID++;
        paymentAmount = pay;
        paymentDate = new Date();
        period = new Period();
        period.setStartDate(paymentDate);
        Date endDate = getPaymentDate();
        endDate.setMonth(getPaymentDate().getMonth() + 2);
        period.setEndDate(endDate);
    }

    @Override
    public String toString()
    {
        return "$" + paymentAmount;
    }

    public void setPaymentDate(Date paymentDate)
    {
        this.paymentDate = paymentDate;
    }

    public void setPeriod(Period period)
    {
        this.period = period;
    }

    public void setPaymentAmount(double paymentAmount)
    {
        this.paymentAmount = paymentAmount;
    }

    public static void setID(int ID)
    {
        Fee.ID = ID;
    }

    public boolean isPaid()
    {
        return paid;
    }

    public void setPaid(boolean paid)
    {
        this.paid = paid;
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
