package RentalPropertyManagementSystem.Users;

import RentalPropertyManagementSystem.Client.Container.Account;

public class Landlord extends AccountHolder
{

    public Landlord(String fname, String lname, String mail, Account account)
    {
        super(fname, lname, mail, account);
    }
}
