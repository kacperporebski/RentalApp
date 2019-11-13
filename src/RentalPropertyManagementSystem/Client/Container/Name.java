package RentalPropertyManagementSystem.Client.Container;

public class Name
{
    String fname;
    String lname;

    public Name(String first, String last)
    {
        fname = first;
        lname = last;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }
}
