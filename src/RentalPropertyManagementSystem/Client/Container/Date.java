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
    int get_day() {
        return day;
    }

    int get_month() {
        return month;
    }
    int get_year()  {
        return year;
    }

    void set_day(int d) {day = d;}
    void set_month(int  m) {
        if(m > 12)
            set_year(get_year()+1);
        month = m % 12;
    }
    void set_year(int y) {year = y;}

    public String toString()
    {
        return day + "/" + month + "/" + year;
    }

}
