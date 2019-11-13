package RentalPropertyManagementSystem.Repositories;

import RentalPropertyManagementSystem.Client.Container.Account;
import RentalPropertyManagementSystem.Users.AccountHolder;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Singleton class which maintains list of account holders
 */
public class UserRepository
{
    private static ArrayList<AccountHolder> users = new ArrayList<>();
    private static UserRepository instance;

    private UserRepository()
    {
        //Private constructor, can only ever have one instance
    }


    public Optional<AccountHolder> addUser(Account account)
    {
        //Todo link users arraylist to that of LoginService or maybe change this to Registration
        Optional<AccountHolder> user = validate(account.getUsername(), account.getPassword());
        if(user.isPresent())
            users.add(user.get());
        return user;
    }

    public Optional<AccountHolder> login(String username, String password)
    {
        return validate(username, password);
    }

    public Optional<AccountHolder> validate(String username, String password)
    {
        //TODO validate
        for (AccountHolder a : users)
        {
            if(a.getUsername().equals(username) && a.getPassword().equals(password))
                return Optional.ofNullable(a);
        }
        return Optional.empty();
    }

    public static UserRepository getInstance()
    {
        if(instance == null)
            instance = new UserRepository();
        return instance;
    }
}
