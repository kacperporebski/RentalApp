package Users;

import Client.Container.Account;

public class Landlord extends AccountHolder
{

    public Landlord(String fname, String lname, String mail, Account account)
    {
        super(fname, lname, mail, account);
    }

    @Override
    public String toString() {
        return "Name " + getName().getFname() + " " + getName().getLname()
                + " Username: " +getUsername() + " Password: " + getPassword();
    }
}
