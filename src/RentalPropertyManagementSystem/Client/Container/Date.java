package RentalPropertyManagementSystem.Client.Container;


import java.time.LocalDate;
import java.time.ZoneId;

public class Date {
    private int day, month, year;

    public Date()
    {

        LocalDate today = LocalDate.now(ZoneId.of("America/Edmonton"));
        day = today.getDayOfMonth();
        month = today.getMonthValue();
        year = today.getYear();

    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public Date(int d, int m, int y)
    {
        day = d;
        month = m;
        year = y;

    }

    public void setDay(int day) {
        this.day = day;
    }

    void setMonth(int  m) {
        if(m > 12)
            setYear(getYear()+1);
        month = m % 12;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString()
    {
        return day + "/" + month + "/" + year;
    }

}
