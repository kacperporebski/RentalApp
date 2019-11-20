package RentalPropertyManagementSystem.Users;

import RentalPropertyManagementSystem.Client.Container.Account;
import RentalPropertyManagementSystem.Client.Container.Name;
import RentalPropertyManagementSystem.Client.Container.UserType;

public abstract class AccountHolder
{
    Name name;
    String email;
    Account account;

    public AccountHolder(String fname, String lname, String mail, Account account)
    {
        name = new Name(fname, lname);
        email = mail;
        this.account  = account;
    }

    public String getUsername(){
        return account.getUsername();
    }
    public String getPassword(){
        return account.getPassword();
    }
    public UserType getAccountType(){
        return account.getAccountType();
    }

    public Name getName() {
        return name;
    }

    public Account getAccount() {
        return account;
    }

    public String getEmail() {
        return email;
    }

    //TODO DO I NEED ANYTHING EXTRA HERE?


    @Override
    public String toString()
    {
        return "Name " + getName().getFname() + " " + getName().getLname()
            + " Username: " +getUsername() + " Password: " + getPassword();
    }
}
