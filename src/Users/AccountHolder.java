package Users;

import Client.Container.Account;
import Client.Container.Name;

public abstract class AccountHolder
{
    Name name;
    String email;

    private Account account;

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

    public Name getName() {
        return name;
    }
    //TODO DO I NEED ANYTHING EXTRA HERE?


}
