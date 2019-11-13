package Client.Repositories;

import Client.Container.Account;
import Client.Container.Observer;
import Client.Container.Subject;
import Users.AccountHolder;
import Users.Landlord;

import java.util.ArrayList;

/**
 * Singleton class which maintains list of account holders
 */
public class UserRepository
{
    private static ArrayList<AccountHolder> users;
    private static UserRepository instance;

    private UserRepository()
    {
        users = new ArrayList<AccountHolder>();

    }


    public void addUser(AccountHolder u)
    {

        users.add(u);




        /*//Todo link users arraylist to that of LoginService or maybe change this to Registration
        AccountHolder user = validate(account.getUsername(), account.getPassword());
        if(user == null)
            users.add(user);
        return user;

         */
    }

    public AccountHolder addUser(Account account){
        AccountHolder user = validate(account.getUsername(), account.getPassword());
        if(user == null)
            users.add(user);
        return user;
    }


    public Landlord findLandlord(String first, String last){
        for(AccountHolder x : users){
            if(( x.getName().getFname().compareTo(first) == 0 ) && (x.getName().getLname().compareTo(last) == 0) ) {
                return (Landlord) x;
            }
        }
        return null;
    }

    public AccountHolder login(String username, String password)
    {
        return validate(username, password);
    }

    public AccountHolder validate(String username, String password)
    {
        //TODO validate
        for (AccountHolder a : users)
        {
            if(a.getUsername().equals(username) && a.getPassword().equals(password))
                return a;
        }
        return null;
    }

    public static UserRepository getInstance()
    {
        if(instance == null)
            instance = new UserRepository();
        return instance;
    }


    public void printAll(){
        for(AccountHolder x : users){
            System.out.println(x.getName().getFname() + x.getName().getLname() + x.getUsername() + x.getPassword());
        }

    }
}
