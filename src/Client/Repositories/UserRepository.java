package Client.Repositories;

import Client.Container.Account;
import Client.Container.Observer;
import Client.Container.Subject;
import Users.AccountHolder;

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
    }


    public AccountHolder addUser(Account account)
    {
        //Todo link users arraylist to that of LoginService or maybe change this to Registration
        AccountHolder user = validate(account.getUsername(), account.getPassword());
        if(user == null)
            users.add(user);
        return user;
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
}
