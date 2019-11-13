package RentalPropertyManagementSystem.Client.Container;


public class Date {
    private int day, month, year;

    public Date()
    {
        //Todo default constructor that sets today's date
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
    void set_month(int  m) {month = m;}
    void set_year(int y) {year = y;}

    public String toString()
    {
        return day + "/" + month + "/" + year;
    }

}
