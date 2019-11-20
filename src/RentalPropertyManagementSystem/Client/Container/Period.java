package RentalPropertyManagementSystem.Client.Container;

public class Period
{
    private Date startDate;
    private Date endDate;

    public Period(){
        startDate = new Date();
        endDate = new Date();
    }

    public Period(int m, int y){
        startDate.setDay(1);
        startDate.setMonth(m);
        startDate.setYear(y);

        int d = 30;
        if(m == 2) {
            d = 28;
        } else if((m % 2 == 0 && m >= 8) || (m % 2 == 1 && m <=7)){
            d = 31;
        }

        endDate.setDay(d);
        endDate.setMonth(m);
        endDate.setYear(y);
    }

    public String toString() {
        String month = "";
        switch(startDate.getMonth()) {
            case 1:
                month = "January";
                break;
            case 2:
                month = "February";
                break;
            case 3:
                month = "March";
                break;
            case 4:
                month = "April";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "June";
                break;
            case 7:
                month = "July";
                break;
            case 8:
                month = "August";
                break;
            case 9:
                month = "September";
                break;
            case 10:
                month = "October";
                break;
            case 11:
                month = "November";
                break;
            case 12:
                month = "December";
                break;
        }
       return month + ", " + startDate.getYear();
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
